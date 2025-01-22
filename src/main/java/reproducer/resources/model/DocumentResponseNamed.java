package reproducer.resources.model;

import io.quarkus.hibernate.orm.panache.common.ProjectedFieldName;

public record DocumentResponseNamed(
        @ProjectedFieldName("document.id")
        Long id,
        String title,
        DocumentTypeResponse documentType) {
}