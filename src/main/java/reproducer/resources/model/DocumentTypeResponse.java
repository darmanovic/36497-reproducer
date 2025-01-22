package reproducer.resources.model;

import io.quarkus.hibernate.orm.panache.common.NestedProjectedClass;

@NestedProjectedClass
public record DocumentTypeResponse (
    Long id,
    String name) {}