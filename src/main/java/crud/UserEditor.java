package crud;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class UserEditor extends VerticalLayout {

    private final UserRepository repository;

    /**
     * The currently edited user
     */
    private User user;

    /* Fields to edit properties in User entity */
    CheckBox admin = new CheckBox("Admin");
    TextField name = new TextField("Name");
    TextField age = new TextField("Age");
//    DateField createdDate = new DateField("createdDate");

    /* Action buttons */
    Button save = new Button("Save", FontAwesome.SAVE);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);
    CssLayout actions = new CssLayout(save, cancel, delete);

    Binder<User> binder = new Binder<>(User.class);

    @Autowired
    public UserEditor(UserRepository repository) {

        this.repository = repository;
        binder.forMemberField(age)
                .withConverter(new StringToIntegerConverter(
                        "Could not convert string to integer"));
//        binder.forMemberField(createdDate)
//                .withConverter(new LocalDateToDateConverter());

        // bind using naming convention

        binder.bindInstanceFields(this);

        addComponents(name, age, admin, actions);
//        addComponents(name, age, admin, createdDate, actions);

        // Configure and style components
        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> repository.save(user));
        delete.addClickListener(e -> repository.delete(user));
        cancel.addClickListener(e -> editUser(user));
        setVisible(false);
    }


    public interface ChangeHandler {

        void onChange();
    }

    public final void editUser(User u) {
        if (u == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = u.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            user = repository.findOne(u.getId());
        }
        else {
            user = u;
        }
        cancel.setVisible(persisted);

        binder.setBean(user);

        setVisible(true);

        // A hack to ensure the whole form is visible
        save.focus();
        // Select all text in name field automatically
        name.selectAll();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
    }
}

