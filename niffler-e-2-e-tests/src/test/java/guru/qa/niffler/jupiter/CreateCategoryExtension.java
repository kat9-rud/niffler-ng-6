package guru.qa.niffler.jupiter;

import guru.qa.niffler.api.SpendApiClient;
import guru.qa.niffler.model.CategoryJson;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

import static guru.qa.niffler.util.CategoryNameGenerator.generateRandomCategoryName;

public class CreateCategoryExtension implements BeforeEachCallback, ParameterResolver, AfterTestExecutionCallback {
    public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext
            .Namespace.create(CreateCategoryExtension.class);
    private final SpendApiClient spendApiClient = new SpendApiClient();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), Category.class)
                .ifPresent(
                        category -> {
                            CategoryJson categoryJson = new CategoryJson(
                                    null,
                                    category.name().equals("") ? generateRandomCategoryName() : category.name(),
                                    category.username(),
                                    false
                            );
                            CategoryJson createdCategory = spendApiClient.createCategory(categoryJson);
                            if (category.archived()){
                                CategoryJson archivedCategory = new CategoryJson(
                                        createdCategory.id(),
                                        createdCategory.name(),
                                        createdCategory.username(),
                                        true
                                );
                                createdCategory = spendApiClient.updateCategory(archivedCategory);
                            }
                            context.getStore(NAMESPACE).put(context.getUniqueId(), createdCategory);
                        }
                );
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(CategoryJson.class);
    }

    @Override
    public CategoryJson resolveParameter(ParameterContext parameterContext,
                                         ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(NAMESPACE).get(
                extensionContext.getUniqueId(),
                CategoryJson.class
        );
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        CategoryJson category = context.getStore(CreateCategoryExtension.NAMESPACE)
                .get(context.getUniqueId(), CategoryJson.class);
        if (!category.archived()) {
            CategoryJson archivedCategory = new CategoryJson(
                    category.id(),
                    category.name(),
                    category.username(),
                    true
            );
            spendApiClient.updateCategory(archivedCategory);
        }
    }
}
