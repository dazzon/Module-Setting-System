package de.dazzon.setting;

import java.util.ArrayList;
import java.util.List;

/**
 * The manager, registering all settings, where each setting can be accessed from.
 *
 * @author dazzon
 * @since 20.06.2018
 */
public class SettingManager {
    /**
     * The list all settings are contained in.
     */
    protected final List<Setting> settings;

    /**
     * SettingManager constructor.
     */
    public SettingManager() {
        this.settings = new ArrayList<>();
    }

    /**
     * @return the list all settings are contained in
     */
    public List<Setting> getSettings() {
        return this.settings;
    }

    /**
     * Returns all settings of the given parent module.
     *
     * @param parent the parent module the settings have to be of
     * @return the settings of the parent module
     */
    public List<Setting> getSettings(Module parent) {
        final List<Setting> filteredSettings = new ArrayList<>();
        for (Setting setting : this.settings) {
            if (setting.getParent() == parent)
                filteredSettings.add(setting);
        }

        return filteredSettings;
    }

    /**
     * Returns the by parent module and name given setting as its setting instance.
     *
     * @param parent the parent module of the setting
     * @param name the name of the setting
     * @return the setting's instance as a setting
     */
    public Setting getSetting(Module parent, String name) {
        for (Setting setting : this.settings) {
            if (setting.getParent() == parent && setting.getName().equalsIgnoreCase(name))
                return setting;
        }

        return null;
    }
}
