package nl.hva.ict.controllers;

import nl.hva.ict.views.AboutView;
import nl.hva.ict.views.View;

public class AboutController extends Controller {

    private AboutView aboutView;

    public AboutController() {
        aboutView = new AboutView();
    }

    @Override
    public View getView() {
        return aboutView;
    }
}
