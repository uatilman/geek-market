package com.geekbrains.geekmarket.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

// Корректировки:
// Поправить пути к картинкам
// Сделать сохранение истории в таблицу
// Выпилить в контроллере формы для товаров кусок загрузки файлов
// Поправить Order cascade detached entity ...
// Подсчет итоговой стоимости заказа+
// rest security (вариант Юрия), из варианта Юрия взять отображение корзины в навигации и количества позиций
// на бекенде делать фильтрацию и pagination для ajax

// Домашнее задание:
// Покопать доки по ajax и сделать ajax управление корзиной (изменение количества товаров в позиции,+
// удаление товара из корзины, отображение корзины в DataTable, переасчет стоимости позиции/заказа)+


@Controller
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class.getName());

    @RequestMapping("/")
    public String showHomePage() {
        return "index";
    }
}
