package action_cast.view;

import action_cast.widgets.CardPanel;

/**
 * Created by brian on 9/28/2015.
 */
public abstract class BaseCardClass {

    protected String name;
    protected BreadCrumb breadCrumb = null;

    public BaseCardClass(BreadCrumb breadCrumb) {
        this.breadCrumb = breadCrumb;
        breadCrumb.addCrumb(getName());
    }

    public void addCard(BaseCardClass card) {
        getMainPanel().addCard(card);
    }

    public abstract CardPanel getMainPanel();

    public abstract String getName();

    public void onResume() {
        updateDisplay();
    }

    protected abstract void updateDisplay();

    public BreadCrumb getBreadCrumb() {
        return breadCrumb;
    }
}
