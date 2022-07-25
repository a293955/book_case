package com.itheima;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dao.BookDao;
import com.itheima.domain.Book;
import com.itheima.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class App2ApplicationTests {

    @Autowired
    private BookDao bookDao;


    @Autowired
    private BookService bookService;

    @Test
    void testGetById() {
        //bookDao.selectById(1);
        Book book = bookService.getById(3);
        System.out.println(book);
    }

    @Test
    void testGetByPage() {
        IPage<Book> page = new Page<>(2, 5);
        QueryWrapper<Book> qw = new QueryWrapper<>();
        bookDao.selectPage(page, qw);
    }

    @Test
    void testGetBy() {
        String name = "1";
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
        lqw.like(name != null, Book::getName, name);
        bookDao.selectList(lqw);
    }
}
