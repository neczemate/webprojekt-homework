package hu.nye.webprojekt.HomeWork.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Objects;

@Entity
public class BookEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String title;

    private String Author;

    @NotNull
    private Date releaseYear;

    private Integer pageNumber;

    private String category;

    public BookEntity() {

    }

    public BookEntity(Long id, String title, String author, Date releaseYear, Integer pageNumber, String category) {
        this.id = id;
        this.title = title;
        Author = author;
        this.releaseYear = releaseYear;
        this.pageNumber = pageNumber;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Date getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Date releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(Author, that.Author) && Objects.equals(releaseYear, that.releaseYear) && Objects.equals(pageNumber, that.pageNumber) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, Author, releaseYear, pageNumber, category);
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", Author='" + Author + '\'' +
                ", releaseYear=" + releaseYear +
                ", pageNumber=" + pageNumber +
                ", category='" + category + '\'' +
                '}';
    }
}
