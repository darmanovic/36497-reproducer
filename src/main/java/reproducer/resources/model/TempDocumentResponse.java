package reproducer.resources.model;

public record TempDocumentResponse(
        Long id,
        String title,
        DocumentTypeResponse documentType) {
}