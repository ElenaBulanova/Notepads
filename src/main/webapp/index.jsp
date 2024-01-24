<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>home</title>
</head>
<body>
    <div>
        <div>
            <h2>Работа с базой данных "Блокноты"</h2>
            <h3>Задание2.</h3>
            <p>
                <a href="${pageContext.request.contextPath}/all">Показать полную таблицу</a>
            </p>
            <p>
                <a href="${pageContext.request.contextPath}/allCountries">Показать все страны - производители</a>
            </p>
            <p>
                <a href="${pageContext.request.contextPath}/countriesCountNotepads">Показать страны и количество блокнотов в них</a>
            </p>
            <p>
                <a href="${pageContext.request.contextPath}/brandCountNotepads">Показать производителей и количество блонотов каждого производителя</a>
            </p>

            <h3>Задание3.</h3>
            <p>
                <a href="${pageContext.request.contextPath}/countryMinMax">Страны с минимальным и максимальным количеством блокнотов</a>
            </p>
            <p>
                <a href="${pageContext.request.contextPath}/brandMinMax">Производители с минимальным и максимальным количеством блокнотов</a>
            </p>

            <h3>Задание4.</h3>
            <p>
                <a href="${pageContext.request.contextPath}/countryFilter">Фильтр по стране</a>
            </p>
            <p>
                <a href="${pageContext.request.contextPath}/pageTypeFilter">Фильтр по типу страницы</a>
            </p>
            <p>
                <a href="${pageContext.request.contextPath}/pagesAmountFilter">Фильтр по количеству страниц</a>
            </p>

            <h3>Задание5.</h3>
            <p>
                <a href="${pageContext.request.contextPath}/add">Добавить блокнот</a>
            </p>
            <p>
                <a href="${pageContext.request.contextPath}/delete">Удалить блокнот</a>
            </p>
            <p>
                <a href="${pageContext.request.contextPath}/update">Обновить блокнот</a>
            </p>
        </div>
    </div>
</body>
</html>