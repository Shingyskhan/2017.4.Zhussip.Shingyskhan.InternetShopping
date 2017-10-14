package com.demo.ui.adminPage;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import org.vaadin.teemusa.sidemenu.SideMenu;
import com.demo.dao.entity.Person;
import com.demo.dao.entity.Product;
import com.demo.ui.adminPage.AdminPresenter.IAdminView;
import com.demo.ui.login.Login;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import de.steinwedel.messagebox.MessageBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;

@SuppressWarnings("serial")
public class AdminView extends HorizontalLayout implements IAdminView {
	private Grid<Product> grid;
	private Grid<Person> grid1;
	private AdminPresenter presenter;
	private Button add;
	private Button delete, delete1;
	protected static Product product;
	VerticalLayout vl1, vl2;
	
	
	
	private TextField name;
	private TextField quantity;
	private TextArea description;
	private TextField price;
	private Binder<Product> binder;
	private Button save;
//	private AddProductPresenter presenter;
	private DateField date;
	private Button back;
	
	
	public AdminView() {
		super();
		presenter = new AdminPresenter(this);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		vl1 = new VerticalLayout();
		vl2 = new VerticalLayout();
		

		SideMenu menu = new SideMenu();
		menu.setSizeFull();
		menu.setMenuCaption("Admin Page");
		menu.addMenuItem("Hello "+Login.person.getName(),()->{});
		menu.addMenuItem("Users", () -> {
			try {
				vl2.removeAllComponents();
				removeComponent(vl1);

			} catch (Exception e) {

			}
			users();
			vl2.setSizeFull();

			addComponent(vl2);
			setComponentAlignment(vl2, Alignment.MIDDLE_LEFT);
			Notification.show("Here is my custom action for this menu item.");

		});
		menu.addMenuItem("Products", () -> {

			try {
				vl1.removeAllComponents();
				removeComponent(vl2);
			} catch (Exception e) {

			}

			products();
			vl1.setSizeFull();

			addComponent(vl1);
			setComponentAlignment(vl1, Alignment.MIDDLE_LEFT);

		});
		menu.addNavigation("log Out", ""); 
		addComponent(menu);
		//setSizeFull();
	}

	private void products() {
		List<Product> products = presenter.findAll();
		grid = new Grid<>(Product.class);
		grid.setSizeFull();
		grid.setItems(products);
		grid.setColumns("id", "name", "quantity", "description", "price");
		grid.addColumn(person -> "Update", new ButtonRenderer<>(clickEvent -> updateProduct(clickEvent.getItem())));
		delete = new Button("delete");
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.asMultiSelect().addSelectionListener(even -> {
			Set<Product> selected = even.getAllSelectedItems();
			delete.addListener(e -> delete(selected));
		});

		
		FormLayout form =new FormLayout();
		form.setSizeFull();
		name=new TextField("name");
		quantity=new TextField("quantity");
		description=new TextArea("description");
		price=new TextField("price");
		date=new DateField("Date of price");
		date.setValue(LocalDate.now());
		date.setTextFieldEnabled(false);
		binder = new Binder<>(Product.class);
		binder.forField(quantity).withConverter(new StringToIntegerConverter("Must be a number value")).bind("quantity");
		binder.forField(price).withConverter(new StringToIntegerConverter("Must be a number value")).bind("price");
		binder.bindInstanceFields(this);
		form.addComponents(name,quantity,description,price,date);
		save=new Button("Add",e -> save());
		VerticalLayout v=new VerticalLayout();
		VerticalLayout layout=new VerticalLayout();
		v.setSizeFull();
		v.addComponents(form,save);
		layout.setSizeFull();
		layout.addComponents(grid,delete);
		vl1.setSizeFull();
		vl1.addComponent(new HorizontalLayout(layout,v));
	}



	private void save() {
		Product product=new Product();
		boolean writeBeanIfValid = binder.writeBeanIfValid(product);
		if (writeBeanIfValid) {
			presenter.saveProduct(product);
			
		} else {
			Notification.show("Check form");
		}
	}

	private void users() {
		delete1 = new Button("delete1");
		grid1 = new Grid<>(Person.class);
		grid1.setSizeFull();
		List<Person> persons = presenter.findAllPerson();
		grid1.setItems(persons);
		grid1.setColumns("id", "name", "surname", "username", "password", "age", "birthDate", "gender");
		grid1.setSelectionMode(SelectionMode.MULTI);
		grid1.asMultiSelect().addSelectionListener(even -> {
			Set<Person> selected = even.getAllSelectedItems();
			delete1.addListener(e -> deletePerson(selected));
		});
		vl2.setSizeFull();
		vl2.addComponent(grid1);
		vl2.addComponent(new HorizontalLayout(delete1));

	}

	
	
	
	
	
	private void deletePerson(Set<Person> selected) {
		for (Person person : selected) {
			if (person.getUsername().equals("s")) {
				Notification.show("You can't delete adminstrator");
			} else {
				presenter.deletePerson(person);
			}

		}
	}

	private void updateProduct(Product item) {
		product = item;
		UI.getCurrent().getNavigator().navigateTo("updateproduct");
	}

	@Override
	public void add() {
		UI.getCurrent().getNavigator().navigateTo("createproduct");

	}

	@Override
	public void update() {

	}

	@Override
	public void delete(Set<Product> selected) {
		MessageBox.createQuestion().withCaption("DELETE").withMessage("Do you really want to delete selected items ?")
				.withYesButton(() -> {
					for (Product product : selected) {
						presenter.delete(product);
					}
				}).withNoButton(() -> {
				}).open();

	}

	@Override
	public void updateList() {
		List<Product> list = presenter.findAll();
		grid.setItems(list);

	}

	@Override
	public void updatePerson() {
		List<Person> list = presenter.findAllPerson();
		grid1.setItems(list);

	}

}
