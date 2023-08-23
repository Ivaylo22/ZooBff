Custom operation explanation:

1 - I've added additional functionality that aims to apply discounts to different items based on their tags. This functionality utilizes the current day of the week to determine which specific tag should receive a 10% discount. All items with this designated tag are subject to a 10% reduction in price, applicable for that day only. The discount is calculated when the user places an order. Additionally, a separate endpoint has been implemented to enable users to review all items that are currently being offered at a discounted rate for the day.



2 - This feature optimizes item extraction from multiple storage locations based on their proximity to a given city. Here's how it works:

-Each storage has a city field added to the database.
-Specify a city when extracting items and provide input quantity.
-The system sorts storage locations by proximity to the city.
-Begin extraction from the closest storage, allocating the input quantity.
-If the quantity is insufficient, additional items are allocated from farther storages.