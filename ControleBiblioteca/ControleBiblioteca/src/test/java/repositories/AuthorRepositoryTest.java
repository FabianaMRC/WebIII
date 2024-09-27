package repositories;

import br.edu.ifpr.controlebiblioteca.models.Author;
import br.edu.ifpr.controlebiblioteca.repositories.AuthorRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AuthorRepositoryTest {

    @Test
    public void deveInserirUmRegistroNaTabelaAuthor() {
        AuthorRepository repository = new AuthorRepository();

        Author authorFake = new Author();
        authorFake.setName("Paulo Coelho");

        repository.insert(authorFake);
    }

    @Test
    public void deveAtualizarONomeDeUmAuthor() {
        AuthorRepository repository = new AuthorRepository();
        repository.updateAuthorName(1, "Fabiana Miranda");
    }

    @Test
    public void deveDeletarUmAuthor() {
        AuthorRepository repository = new AuthorRepository();
       repository.delete(1);
    }

    @Test
    public void deveRetornarUmaListaDeTodosOsAutores() {
        AuthorRepository repository = new AuthorRepository();
        List<Author> authors = repository.getAll();

        System.out.println("*********** Lista de Todos os Autores: *************");

        for (Author author : authors) {
            System.out.println(author);
        }
        System.out.println("*************************************************************\n");
    }



}

