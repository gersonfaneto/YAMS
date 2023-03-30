package com.gersonfaneto.techinfo.dao.component;

import com.gersonfaneto.techinfo.models.stock.component.Component;
import com.gersonfaneto.techinfo.models.stock.component.ComponentType;

import java.util.*;

public class ComponentListCRUD implements ComponentDAO {
    private final Map<String, Component> registeredComponents;

    public ComponentListCRUD() {
        this.registeredComponents = new HashMap<>();
    }

    @Override
    public Component createOne(Component newComponent) {
        String newID = UUID.randomUUID().toString();

        newComponent.setComponentID(newID);

        registeredComponents.put(newID, newComponent);

        return newComponent;
    }

    @Override
    public Component findByID(String targetID) {
        return registeredComponents.get(targetID);
    }

    @Override
    public List<Component> findMany() {
        List<Component> foundComponents = new LinkedList<>();

        for (Component currentComponent : registeredComponents.values()) {
            foundComponents.add(currentComponent);
        }

        return foundComponents;
    }

    @Override
    public boolean updateInformation(Component targetComponent) {
        String componentID = targetComponent.getComponentID();

        if (registeredComponents.containsKey(componentID)) {
            registeredComponents.put(componentID, targetComponent);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteByID(String targetID) {
        if (registeredComponents.containsKey(targetID)) {
            registeredComponents.remove(targetID);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteMany() {
        if (!registeredComponents.isEmpty()) {
            registeredComponents.clear();
            return true;
        }

        return false;
    }

    @Override
    public List<Component> findByType(ComponentType targetType) {
        List<Component> foundComponents = new LinkedList<>();

        for (Component currentComponent : registeredComponents.values()) {
            if (currentComponent.getComponentType() == targetType) {
                foundComponents.add(currentComponent);
            }
        }

        return foundComponents;
    }
}
