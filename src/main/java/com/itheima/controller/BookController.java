package com.itheima.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.domain.Book;
import com.itheima.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Result save(@RequestBody Book book) {
        boolean flag = bookService.save(book);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @PutMapping
    public Result update(@RequestBody Book book) {
        boolean flag = bookService.updateById(book);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean flag = bookService.removeById(id);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Book book = bookService.getById(id);
        String msg = book != null ? "" : "数据查询失败，请重试！";
        return new Result(book != null ? Code.GET_OK : Code.GET_ERR, book, msg);
    }

    @GetMapping
    public Result getAll() {
        List<Book> bookList = bookService.list();
        String msg = bookList != null ? "" : "数据查询失败，请重试！";
        return new Result(bookList != null ? Code.GET_OK : Code.GET_ERR, bookList, msg);
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public Result getPage(@PathVariable int currentPage, @PathVariable int pageSize, Book book) {
        IPage<Book> books = bookService.getPage(currentPage, pageSize, book);
        //如果当前页大于最大页码值，使用最大页码值作为当前页进行查询
        if (currentPage > books.getPages()) {
            books = bookService.getPage((int) books.getPages(), pageSize, book);
        }
        String msg = books != null ? "" : "数据查询失败，请重试！";
        return new Result(books != null ? Code.GET_OK : Code.GET_ERR, books, msg);
    }

}
