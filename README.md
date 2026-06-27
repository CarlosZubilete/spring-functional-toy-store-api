## Instructions

1. Get the names of the toys that have `stock > 0` and have sold more than 50 units in the last 30 days. They must be
   sorted in descending order by sales.

2. Calculate the average price of the toys in the `EDUCATIONAL` category.

3. Generate a `Map<Category, Long>` grouping the total quantity of toys per category.

4. Get the most expensive toy within the `BOARD_GAME` category, returning an `Optional` in case it does not exist.
5. Get the top 5 toys with the highest average rating, ignoring those with a null rating.

6. Generate a `Set<String>` containing all existing tags in the catalog, without duplicates and in lowercase (Hint: use
   `flatMap`).

7. Calculate the total potential inventory value by category (price * stock), returning a `Map<String, Double>`.

8. Generate a list of restock alerts for toys with a stock of less than 5.

---