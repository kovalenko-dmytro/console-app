package com.kovalenko.application.view.render;

import com.kovalenko.application.view.view.View;

import java.util.Objects;

public class ViewRenderer implements Renderer {

    @Override
    public void render(Object view) {
        if (Objects.isNull(view)) {
            return;
        }
        if (view instanceof String) {
            System.out.println(view);
        }
        if (view instanceof View) {
            ((View)view).render();
        }
    }
}
