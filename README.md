# ikea-assignment
Assignment for discussion during technical interview

### Improvements
- Rather than having the InventoryManager and ProductManager hold the contents (Articles and Product) in a list I should maybe have looked into a solution with a HashMap
- Possibly refactor WareHouse even more, specifically break down updateAvailableProducts and SellProducts methods
- Forgot to use StringBuilder in some cases (used concatenation instead)
- InventoryManager and ProductManager could have been inserted in the WareHouse constructor instead (for testing purposes)
- Missing test cases (JUnit)

### About
- Parsing json using Jackson (I map each key in the json to a jsonproperty attribute)

- Inventory consists of Article(s)

- Products consists of Product (maps 'name' and 'contain_articles' keys) which in turn consists of ProductArticle (maps 'art_id' and 'amount_of' keys)

- Warehouse holds Inventory + Product (maps 'art_id', 'name' and 'stock')

- FileLoader is an interface which is implemented by both the InventoryManager and ProductManager to import the json files

- Both Inventory.java and Products.java are classes used to map the two json files to these classes (I'm more used to structs in Swift)
