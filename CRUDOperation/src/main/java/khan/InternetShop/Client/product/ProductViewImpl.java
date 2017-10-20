package khan.InternetShop.Client.product;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.MessageBox;
import khan.InternetShop.Client.product.ProductPresenter.IProductView;
import khan.InternetShop.Server.entity.Product;

import java.util.Set;

public class ProductViewImpl extends VerticalLayout implements IProductView {

    private ProductPresenter presenter;
    private Grid<Product> grid;
    private Button btnAdd;
    private Button btnDelete;
    private Button btnEdit;
    private FormLayout fl;
    private TextField name;
    private TextField description;
    private TextField quantity;
    private Binder<Product> binder;
    private Button btnSave;
    private Button btnUpdate;
    private Button btnCancel;
    private Product bean;
    Window windowView = new Window("ProductView");

    public ProductViewImpl() {
        super();
        this.presenter = new ProductPresenter(this);
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setSizeFull();
        HorizontalLayout main = new HorizontalLayout();
        main.setSizeFull();
        btnAdd = new Button("Add", e -> doAdd());
        btnEdit = new Button("Edit", clickEvent -> doEdit());
        btnDelete = new Button("Delete");

        btnAdd.setIcon(FontAwesome.PLUS);
        btnDelete.setIcon(FontAwesome.TRASH);
        btnEdit.setIcon(FontAwesome.PENCIL);
        btnAdd.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        btnDelete.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        btnEdit.addStyleName(ValoTheme.BUTTON_ICON_ONLY);

        grid = new Grid<Product>(Product.class);
        grid.addStyleName(ValoTheme.TABLE_COMPACT);
        grid.setItems(presenter.findAll());
        grid.setSizeFull();
        grid.setColumns("id", "name", "description", "productCategory", "quantity");
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.asMultiSelect().addSelectionListener(even -> {
            Set<Product> selected = even.getAllSelectedItems();
            btnDelete.addListener(e -> delete(selected));
        });

        grid.addItemClickListener(e -> bean = e.getItem());

        btnSave = new Button("Save", e -> doSave());

        btnUpdate = new Button("Update", e -> doUpdate());
        btnCancel = new Button("Close", e -> doClose());
        VerticalLayout buttonLayout = new VerticalLayout();
        buttonLayout.addComponents(btnAdd, btnEdit, btnDelete);
        buttonLayout.setMargin(false);
        buttonLayout.setExpandRatio(btnDelete, 1);
        main.addComponents(grid, buttonLayout);
        main.setExpandRatio(grid, 92);
        main.setExpandRatio(buttonLayout, 8);
        addComponent(main);

    }

    private void delete(Set<Product> selected) {
        MessageBox.createQuestion().withCaption("DELETE").withMessage("Do you really want to delete selected items ?")
                .withYesButton(() -> {
                    presenter.deleteMany(selected);
                }).withNoButton(() -> {
        }).open();
    }

    private void doEdit() {
        btnUpdate.setVisible(true);
        btnSave.setVisible(false);
        showWindow();
        binder.setBean(bean);

    }

    private void doAdd() {
        btnSave.setVisible(true);
        btnUpdate.setVisible(false);
        showWindow();



    }


    public void showWindow() {

        UI.getCurrent().addWindow(windowView);
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        fl = new FormLayout();
        fl.setSizeFull();
        name = new TextField("name");
        name.setSizeFull();
        description = new TextField("description");
        description.setSizeFull();
        quantity = new TextField("quantity");
        quantity.setSizeFull();
        binder = new Binder<>(Product.class);
        binder.forField(quantity).withConverter(new StringToIntegerConverter("Must be a number")).bind("quantity");
        binder.bindInstanceFields(this);
        fl.addComponents(name, description, quantity);
        fl.setSpacing(true);
        fl.setMargin(true);

        btnSave.addStyleName(ValoTheme.BUTTON_PRIMARY);
        btnSave.addStyleName(ValoTheme.BUTTON_SMALL);
        btnUpdate.addStyleName(ValoTheme.BUTTON_SMALL);
        btnCancel.addStyleName(ValoTheme.BUTTON_SMALL);
        btnCancel.addStyleName(ValoTheme.BUTTON_DANGER);
        btnUpdate.addStyleName(ValoTheme.BUTTON_PRIMARY);
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setSizeFull();
        buttonLayout.addComponents(btnSave, btnUpdate, btnCancel);
        buttonLayout.setExpandRatio(btnUpdate, 1);
        if (!btnUpdate.isVisible()) {
            buttonLayout.setExpandRatio(btnSave, 1);
        }
        mainLayout.addComponents(fl, buttonLayout);
        mainLayout.setExpandRatio(fl, 90);
        mainLayout.setExpandRatio(buttonLayout, 10);
        windowView.setContent(mainLayout);
        windowView.center();
        windowView.setHeight("400px");
        windowView.setWidth("600px");
        windowView.setResizable(false);
        windowView.setCloseShortcut(ShortcutAction.KeyCode.ESCAPE, null);
        windowView.setModal(true);
        windowView.focus();
    }

    private void doClose() {
        presenter.delete(bean.getId());
    }

    private void doUpdate() {

        presenter.update(bean);
    }

    private void doSave() {
        Product product = new Product();
        boolean writeBeanIfValid = binder.writeBeanIfValid(product);
        if (writeBeanIfValid) {
            presenter.save(product);
        } else {
            Notification.show("Check form");
        }
    }


    @Override
    public void updateList() {
        grid.setItems(presenter.findAll());
    }
}
