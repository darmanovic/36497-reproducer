package reproducer;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import reproducer.repos.DocumentRepository;
import reproducer.resources.model.DocumentResponseNamed;
import reproducer.resources.model.TempDocumentResponse;

@QuarkusTest
class ReproducerTest {

    @Inject
    DocumentRepository repo;

    @Test
    @Order(1)
    void expected() {
        var q = repo
                .find("FROM Document document LEFT JOIN document.documentType documentType WHERE documentType.id IS NULL OR documentType.id > 0")
                .project(DocumentResponseNamed.class);

        var data = q.list();
        var count = q.count();

        Assertions.assertEquals(4, count); // pass, count is 4
        Assertions.assertEquals(data.size(), count);
    }

    @Test
    @Order(2)
    void reproducer1() {
        // Notice lack of "documentType" association name.
        var q = repo
                .find("FROM Document document LEFT JOIN document.documentType WHERE documentType.id IS NULL OR documentType.id > 0")
                .project(DocumentResponseNamed.class);

        var data = q.list();
        var count = q.count();

        Assertions.assertEquals(4, count); // fails, count is 3
        Assertions.assertEquals(data.size(), count);
    }

    @Test
    @Order(3)
    void reproducer2() {
        var q = repo
                .find("documentType.id IS NULL OR documentType.id > 0")
                .project(TempDocumentResponse.class);

        var data = q.list();
        var count = q.count();

        Assertions.assertEquals(4, count); // fails, count is 3
        Assertions.assertEquals(data.size(), count);
    }

}