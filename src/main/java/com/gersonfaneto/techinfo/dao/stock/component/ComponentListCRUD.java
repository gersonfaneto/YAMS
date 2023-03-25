package com.gersonfaneto.techinfo.dao.stock.component;

import com.gersonfaneto.techinfo.models.stock.Component;
import com.gersonfaneto.techinfo.models.stock.ComponentType;

import java.util.LinkedList;
import java.util.List;

public class ComponentListCRUD implements ComponentDAO {
    private List<Component> componentList;
    private int referenceID;

    public ComponentListCRUD() {
        this.componentList = new LinkedList<>();
        this.referenceID = 0;
    }

    @Override
    public Component createOne(Component targetObject) {
        targetObject.setComponentID(++referenceID);
        componentList.add(targetObject);
        return targetObject;
    }

    @Override
    public Component findByID(int targetID) {
        for (Component currentComponent : componentList) {
            if (currentComponent.getComponentID() == targetID) {
                return currentComponent;
            }
        }

        return null;
    }

    @Override
    public List<Component> findMany() {
        return componentList;
    }

    @Override
    public boolean updateInformation(Component targetObject) {
        for (Component currentComponent : componentList) {
            if (currentComponent.getComponentID() == targetObject.getComponentID()) {
                int targetIndex = componentList.indexOf(currentComponent);
                componentList.set(targetIndex, targetObject);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteByID(int targetID) {
        for (Component currentComponent : componentList) {
            if (currentComponent.getComponentID() == targetID) {
                componentList.remove(currentComponent);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteMany() {
        if (!componentList.isEmpty()) {
            componentList.clear();
            return true;
        }

        return false;
    }

    @Override
    public List<Component> findByType(ComponentType targetType) {
        List<Component> foundComponents = new LinkedList<>();

        for (Component currentComponent : componentList) {
            if (currentComponent.getComponentType() == targetType) {
                foundComponents.add(currentComponent);
            }
        }

        return foundComponents;
    }
}
