 -- Список всех заказов за последние 7 дней с именем покупателя и описанием товара.
SELECT c.first_name,
	   c.last_name,
	   p.description,
	   o.quantity,
	   o.order_date
	FROM orders o
	INNER JOIN customer c ON o.customer_id = c.customer_id
	INNER JOIN product p ON o.product_id = p.product_id
	WHERE o.order_date  BETWEEN '2025-09-04' AND '2025-09-10'
	ORDER BY o.order_date ASC;

--Топ-3 самых популярных товара.
SELECT  p.description, count(p.description) cd
	FROM orders o
	INNER JOIN product p ON o.product_id = p.product_id
	GROUP BY p.description
	ORDER BY cd DESC
	LIMIT 3;

--Найти все заказы, совершенные клиентами с именем "Константин".
SELECT *
	FROM orders
	WHERE customer_id in (
	SELECT customer_id
	FROM customer
	WHERE first_name = 'Константин');

-- Список товаров, которые не были куплены ни разу.
SELECT description
	FROM product
	WHERE product_id NOT IN (
	SELECT DISTINCT product_id
	FROM orders);

-- Максимальная цена товара
SELECT
	MAX(cost) AS max_cost
	FROM product

--Обновление количества на складе при покупке.
UPDATE product SET quantity = (quantity - 1)  WHERE description = 'Шкаф';

--Обновление цены товара.
UPDATE product SET quantity = 8100  WHERE description = 'Шкаф';

--Обновление названия кактегории.
UPDATE product SET category = 'Для дома' WHERE category = 'Мебель';

--Удаление клиентов без заказов.
DELETE FROM customer WHERE customer_id NOT IN (
	SELECT DISTINCT c.customer_id
	FROM orders o
	INNER JOIN customer c ON o.customer_id = c.customer_id);

--Удаление товаров по которым небыло заказов.
DELETE FROM product WHERE product_id NOT IN (
	SELECT DISTINCT product_id
	FROM orders);
