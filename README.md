# ikea-assignment
Assignment for discussion during technical interview

### About
- Parsing json using Jackson (I map each key in the json to a jsonproperty attribute)

- Inventory consists of Article(s)

- Products consists of Product which in turn consists of ProductArticle

- Warehouse holds Inventory + Product

- FileLoader is an interface which is implemented by both the InventoryManager and ProductManager to import the json files

- Both Inventory.java and Products.java are classes used to map the two json files to these classes (I'm more used to structs in Swift)
