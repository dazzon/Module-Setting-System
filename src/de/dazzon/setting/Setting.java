package de.dazzon.setting;

import de.dazzon.core.Client;
import de.dazzon.module.Module;

/**
 * Superclass for settings.
 *
 * @author dazzon
 * @since 20.06.2018
 */
public class Setting<T> {
    private final Module parent;
    private final String name;

    private final T min;
    private final T max;

    private final T[] options;

    private T state;

    private boolean visible;

    /**
     * Setting constructor. Defines, beneath the parent module and the name of the setting,
     * its state and whether it is visible.
     *
     * @param parent the parent module of the setting.
     * @param name the name of the setting
     * @param state the state of the setting.
     * @param visible whether the setting is visible.
     */
    public Setting(Module parent, String name, T state, boolean visible) {
        this(parent, name, null, null, null, state, visible);
    }

    /**
     * Setting constructor. Defines, beneath the parent module and the name of the setting,
     * its minimum and maximum, its state and whether it is visible.
     *
     * @param parent the parent module of the setting.
     * @param name the name of the setting.
     * @param min the minimum of the setting.
     * @param max the maximum of the setting.
     * @param state the state of the setting.
     * @param visible whether the setting is visible.
     */
    public Setting(Module parent, String name, T min, T max, T state, boolean visible) {
        this(parent, name, min, max, null, state, visible);
    }

    /**
     * Setting constructor. Defines, beneath the parent module and the name of the setting,
     * its options, its state and whether it is visible.
     *
     * @param parent the parent module of the setting.
     * @param name the name of the setting.
     * @param options the options of the setting.
     * @param state the state of the setting.
     * @param visible whether the setting is visible.
     */
    public Setting(Module parent, String name, T[] options, T state, boolean visible) {
        this(parent, name, null, null, options, state, visible);
    }

    private Setting(Module parent, String name, T min, T max, T[] options, T state, boolean visible) {
        this.parent = parent;
        this.name = name;
        this.min = min;
        this.max = max;
        this.options = options;
        this.state = state;
        this.visible = visible;

        Client.getInstance().getSettingManager().settings.add(this);
    }

    /**
     * @return the parent module of the setting.
     */
    public Module getParent() {
        return this.parent;
    }

    /**
     * @return the name of the setting.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the minimum of the setting.
     */
    public T getMin() {
        return this.min;
    }

    /**
     * @return the maximum of the setting.
     */
    public T getMax() {
        return this.max;
    }

    /**
     * @return the options of the setting.
     */
    public T[] getOptions() {
        return this.options;
    }

    /**
     * @return the state of the setting.
     */
    public T getState() {
        return this.state;
    }

    /**
     * @param state defines the new state of the setting.
     */
    public void setState(T state) {
        this.state = state;
        this.onStateUpdate();
    }

    /**
     * @return whether the setting is visible.
     */
    public boolean isVisible() {
        return this.visible;
    }

    /**
     * @param visible defines the new state of the visibility of the setting.
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Called when the state of the setting is updated.
     */
    public void onStateUpdate() {}
}
