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

## Screenshots
![Screenshot from 2023-09-19 16-13-42](https://github.com/CRY-D/TextComponentPopUpBuilder/assets/16564491/10b2e4ff-f68d-4bcb-b4e4-7f39a63543a3)
![Screenshot from 2023-09-19 16-13-57](https://github.com/CRY-D/TextComponentPopUpBuilder/assets/16564491/70c3218f-5953-4d41-97cc-cfd7158ec016)
![Screenshot from 2023-09-19 16-14-04](https://github.com/CRY-D/TextComponentPopUpBuilder/assets/16564491/053a7c1e-4bc0-4df8-8339-1a3a48811381)


![Screenshot from 2023-09-19 16-15-46](https://github.com/CRY-D/TextComponentPopUpBuilder/assets/16564491/2a746908-70d0-4234-a91a-4f17aa25f11e)
![Screenshot from 2023-09-19 16-15-58](https://github.com/CRY-D/TextComponentPopUpBuilder/assets/16564491/37eb777d-836a-419c-97a3-2aae2b51a63b)
![Screenshot from 2023-09-19 16-16-32](https://github.com/CRY-D/TextComponentPopUpBuilder/assets/16564491/c80f1bd7-1e36-4d8f-8786-4219823bd54c)
![Screenshot from 2023-09-19 16-15-32](https://github.com/CRY-D/TextComponentPopUpBuilder/assets/16564491/e155a075-ccdd-45b2-8298-cb2478c55991)


