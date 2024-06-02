# ikea-assignment
Assignment for discussion during technical interview

### Pros
- Realtively 

### Cons
- Currently both Inventory.java and Products.java store their items in a list (affects time complexity when these grow)
- Conversion of the lists to HashMaps are performed in the WareHouse class, possibly move to Inventory and Products
- I load both inventory and products inside of WareHouse, I should have refactored it a bit. I thought of creating two interfaces for loading inventory and products separately each with a load method + getter + map attribute, and then implement the interfaces. Then just use references to the implemented interfaces and call their respective load functions inside of WareHouse


### About
- Parsing json using Jackson (I map each key in the json to a jsonproperty attribute)

- Inventory consists of Article(s)

- Products consists of Product which in turn consists of ProductPart

- Warehouse holds Inventory + Product

- Both Inventory.java and Products.java are classes used to map the two json files to these classes (I'm more used to structs in Swift)