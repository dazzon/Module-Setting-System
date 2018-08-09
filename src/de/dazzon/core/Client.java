package de.dazzon.core;

import de.dazzon.module.Module;
import de.dazzon.setting.Setting;
import de.dazzon.setting.SettingManager;

/**
 * Demo Client class for better integration and showcasing.
 *
 * @author dazzon
 * @since 20.06.2018
 */
public class Client {

    private static Client INSTANCE;
    private final SettingManager SETTINGS_MANAGER;

    /**
     * Constructor instantiates a new SettingsManager.
     *
     * */
    public Client() {

        this.SETTINGS_MANAGER = new SettingManager();

    }

    /***
     * Getter for getting the SettingsManager instance.
     *
     * @return SettingsManager
     */
    public SettingManager getSettingManager() {
        return SETTINGS_MANAGER;
    }

    /***
     * Getter for getting the current Client instance.
     *
     * @return Client
     */
    public static Client getInstance() {
        return INSTANCE;
    }

    /***
     * Main method with some examples.
     *
     * @param args
     */
    public static void main(String[] args) {

        INSTANCE = new Client();
        Module demoModule = new Module();
        Setting<Boolean> settingSpam = new Setting<Boolean>(demoModule, "Spam", true, true);
        Setting<Integer> settingAmount = new Setting<Integer>(demoModule, "Amount", 1, 10, 1, true);
        Setting<String> settingMode = new Setting<String>(demoModule, "Mode", new String[] { "Harry", "Ron", "Hermione" }, "Harry", true);
        Setting<String> settingSuffix = new Setting<String>(demoModule, "Suffix", "loves Hogwarts!", true);

    }

}
