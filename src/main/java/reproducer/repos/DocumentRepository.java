package reproducer.repos;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.Dependent;
import reproducer.model.Document;


@Dependent
public class DocumentRepository implements PanacheRepository<Document> {
}
