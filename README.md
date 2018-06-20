# (Module) Setting System

You can use this system for creating and managing settings of the modules of your Minecraft client in an efficient way.
You don't have to give me credit, but if using this, don't claim it as your creation and don't lie to people.

A quick tutorial how to implement and use it:
--

After copying the system into your client path, go to your client class (probably wearing the name of your client or just called "Client") and create an instance and a getter for the [SettingManager](https://github.com/dazzon/Module-Setting-System/blob/master/src/de/dazzon/setting/SettingManager.java), like you have probably done for your ModuleManager or how you called it (it doesn't necessarily need to be a getter from where you get the instance, just handle it the way you're fine).

One very basic example of how it could be done:

```java
public class Client {
    ...
    private final SettingManager settingManager = new SettingManager();
    ...
    public SettingManager getSettingManager() {
        return this.settingManager;
    }
    ...
}
```

Done that, we can now fix the errors caused by the [Setting](https://github.com/dazzon/Module-Setting-System/blob/master/src/de/dazzon/setting/Setting.java) and [SettingManager](https://github.com/dazzon/Module-Setting-System/blob/master/src/de/dazzon/setting/SettingManager.java) class. Therefor head over to these, import your module class for both and modify (if it doesn't already fit) the path of the line ```Client.getInstance().getSettingManager().settings.add(this);``` (at the bottom of the private constructor of [Setting](https://github.com/dazzon/Module-Setting-System/blob/master/src/de/dazzon/setting/Setting.java) line 73) that way, you access the [SettingManager](https://github.com/dazzon/Module-Setting-System/blob/master/src/de/dazzon/setting/SettingManager.java)'s settings-List, ```this``` is added to (remember to import your client class as well).

Now we can start off creating settings for our modules. Therefor open up a module you want to create settings for. In this example the settings' instances are made in the module's constructor:

```java
public class ModuleTest extends Module {
    /* This setting wears a simple Boolean you can toggle on and off. */
    private final Setting<Boolean> settingSpam;
    
    /*
     * This setting wears an Integer with whole (rounded) values.
     * It is given a minimum (1) and maximum (10) value.
     */
    private final Setting<Integer> settingAmount;
    
    /*
     * This setting wears a String. An array of options to
     * choose from is added to it in the constructor.
     */
    private final Setting<String> settingMode;
    
    /*
     * This setting wears a String as well; it is no mode,
     * but a text that can be set with a text field.
     */
    private final Setting<String> settingSuffix;
    ...
    public ModuleTest() {
        ...
        /*
         * If you are confused because of the parameters and their meanings,
         * just have a look at the documentation I added to them.
         */
        this.settingSpam = new Setting<Boolean>(this, "Spam", true, true);
        this.settingAmount = new Setting<Integer>(this, "Amount", 1, 10, 1, true);
        this.settingMode = new Setting<String>(this, "Mode", new String[] { "Harry", "Ron", "Hermione" }, "Harry", true);
        this.settingSuffix = new Setting<String>(this, "Suffix", "loves Hogwarts!", true);
    }
    ...
}
```

You see, the system is suitable for every kind of setting (for check boxes, value sliders, combo boxes or text fields). It's not only limited to that, you can pretty much test around with it. You can also add actions that should happen when updating the state (giving it a new value):

```java
public ModuleTest() {
    ...
    this.settingSpam = new Setting<Boolean>(this, "Spam", true, true) {
        
        @Override
        public void onStateUpdate() {
            /*
             * If the spam setting (here "this") is disabled,
             * all three of the other settings are made invisible;
             * if the spam setting is enabled again,
             * all the other settings are visible again, too
             * (you could also have done that with a loop,
             * but I wanted to illustrate it clearly at this point).
             */
            ModuleTest.this.settingAmount.setVisible(this.getState());
            ModuleTest.this.settingMode.setVisible(this.getState());
            ModuleTest.this.settingSuffix.setVisible(this.getState());
        }
    };
    this.settingAmount = new Setting<Integer>(this, "Amount", 1, 10, 1, true);
    this.settingMode = new Setting<String>(this, "Mode", new String[] { "Harry", "Ron", "Hermione" }, "Harry", true);
    this.settingSuffix = new Setting<String>(this, "Suffix", "loves Hogwarts!", true);
}
```

To set the state of a setting simply use ```.setState(...)```; the state you set has to be the datatype you set when declaring the setting. By the way, it's your task to ensure, that the set states are only those you should be able to set (that means to ensure they're not bigger or smaller than their maximum or minimum etc.) and that invisible settings are hidden (e.g. in your Click GUI).

Now an example how to query or process the settings in the belonging module (you can try this example in your update loop or something similar):

```java
if (this.settingSpam.getState()) {
    for (int i = 0; i < this.settingAmount.getState(); i++)
        System.out.println(this.settingMode.getState() + " " + this.settingSuffix.getState());
}
```

Finally a little explaination on how to check what kind of setting you're dealing with when e.g. iterating all of them in your Click GUI (methods, for getting all or only these of a specific module, can be found in the [SettingManager](https://github.com/dazzon/Module-Setting-System/blob/master/src/de/dazzon/setting/SettingManager.java); read the Javadoc and you should be fine ^^); you just have to query whether the state of the setting is an instance of a specific data type. For example, if I want to check whether the current setting (remember, we're in a loop iterating all registered settings) is a boolean, I'd query:

```java
if (currentSetting instanceof Boolean) {
    ...
}
```

That will help you a lot for limiting settings to their bounds (like minimum, maximum or options) as well. You can also query, whether the minimum, maximum or options are null. The reason I made an extra manager for the settings is that this gives you the opportunity to give a null-parent to a setting and to create them outside a module (for e.g. "global settings").

If you still have questions, just let me know and I'll try my best to help you :)
