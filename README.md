# TextComponentPopUpBuilder
Java Swing TextComponentPopUpBuilder  The is a Java Swing component that allows you to add a popup menu to a text component. It provides a fluent API to configure the popup menu and register the actions globally.
## Usage

You can use the TextComponentPopUpBuilder like this:
```java
new TextComponentPopUpBuilder(component)
    .registerActionsGlobally(rootPane)
    .setPadding(padding)
    .setMenuType(menuType)
    .build();
```
Here, component is the text component to which the popup menu is attached:
- **rootPane** is the root pane where the actions shortcuts will be registered globally (it can be null), 'getRootPane()'
- **padding** is the padding for the popup menu itels (optional) its array of integers
- and **menuType** is the type of the menu. could be:``` MenuType.REGULAR, MenuType.MINIMUM , MenuType.MAXIMUM ```

### Example

Here is an example from the demo frame of how to use the TextComponentPopUpBuilder:
```java
public NewJFrame() {
      initComponents();
      initTextComponet(regularText, MenuType.REGULAR);
      initTextComponet(miniText, MenuType.MINIMUM);
      initTextComponet(maxText, MenuType.MAXIMUM);
  }

  
private void initTextComponet(JTextComponent component, MenuType menuType) {
    new TextComponentPopUpBuilder(component)
            .registerActionsGlobally(rootPane)
            .setPadding(padding)
            .setMenuType(menuType).build();
}
```

### dependencies: 
libs in the dist directory
##Screenshots

![Screenshot from 2023-09-19 16-13-42](https://github.com/CRY-D/TextComponentPopUpBuilder/assets/16564491/f6649a2d-7967-4632-a642-8782b5d6fe74)
![Screenshot from 2023-09-19 16-13-57](https://github.com/CRY-D/TextComponentPopUpBuilder/assets/16564491/c7f35271-ab99-4592-807a-f6b8b6615b7a)
![Screenshot from 2023-09-19 16-14-04](https://github.com/CRY-D/TextComponentPopUpBuilder/assets/16564491/b1990727-15e2-4fc4-96d6-597057fec7fc)

- from real wold app (table cell ediror)
![Screenshot from 2023-09-19 16-15-32](https://github.com/CRY-D/TextComponentPopUpBuilder/assets/16564491/c0c24527-f842-455b-bfdc-40974ac79cba)

![Screenshot from 2023-09-19 16-15-46](https://github.com/CRY-D/TextComponentPopUpBuilder/assets/16564491/a7d95d45-f794-4b8b-aac5-667f7c16fde5)


![Screenshot from 2023-09-19 16-15-58](https://github.com/CRY-D/TextComponentPopUpBuilder/assets/16564491/0d2963b5-5e69-4a91-a67e-d087bd786e0c)

![Screenshot from 2023-09-19 16-16-32](https://github.com/CRY-D/TextComponentPopUpBuilder/assets/16564491/f8de1966-2c43-4894-a94d-f41cbda12ce1)

