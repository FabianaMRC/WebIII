package repositories;
import br.edu.ifpr.controlebiblioteca.models.Author;
import br.edu.ifpr.controlebiblioteca.models.Book;
import br.edu.ifpr.controlebiblioteca.models.BookStatus;
import br.edu.ifpr.controlebiblioteca.repositories.BookRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class BookRepositoryTest {


    @Test
    public void deveInserirUmRegistroNaTabelaBook(){
        BookRepository repository = new BookRepository();

        Book bookFake = new Book();

        bookFake.setName(("Mar Morto"));
        bookFake.setDate(LocalDate.of(2024,04,30));
        bookFake.setStatus(BookStatus.EMPRESTADO);

        Author author = new Author();
        author.setId(1);
        bookFake.setAuthor(author);

        repository.insert(bookFake);
    }

    @Test
    public void deveExibirUmaListaDeLivros() {

        BookRepository repository = new BookRepository();

        List<Book> books = repository.getBooks();

        for (Book b : books) {
            System.out.println(b);
        }
    }

    @Test
    public void deveDeletarUmLivro(){
        BookRepository repository = new BookRepository();
        repository.delete(3);

    }

    @Test
    public void deveRetornarUmLivroPeloId(){
        BookRepository repository = new BookRepository();
        Book book = repository.getById(1);

        System.out.println(book);
    }

    @Test
    public void deveRetornarUmaListaDeLivrosPeloIdDoAuthor() {
        BookRepository repository = new BookRepository();

        List<Book> booksList = repository.findByAuthor(1);

        System.out.println("*********** Lista de livros por autor*******************");
        for (Book book : booksList) {
            System.out.println(book);
        }
        System.out.println("*************************************************************\n");
    }

    @Test
    public void deveAtualizarStatusDoLivro() {
        BookRepository repository = new BookRepository();
        repository.updateStatus(1, BookStatus.EMPRESTADO);


    }














}
