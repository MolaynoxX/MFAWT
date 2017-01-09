package de.molaynoxx.wtracker.tests.api.config;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import de.molaynoxx.wtracker.api.config.Config;
import de.molaynoxx.wtracker.api.config.ConfigValue;
import de.molaynoxx.wtracker.api.config.NumberConfigValue;
import de.molaynoxx.wtracker.api.config.StringConfigValue;
import de.molaynoxx.wtracker.api.exceptions.ConfigValueInvalidTypeException;
import de.molaynoxx.wtracker.api.util.GsonUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void testConfigSimple() throws IOException {
        Config cfg = createDummyConfig();

        cfg.setConfiguration("Integer", 1);
        cfg.setConfiguration("Float", 1.5F);
        cfg.setConfiguration("Double", 3.14D);
        cfg.setConfiguration("Byte", (byte) 0xFF);
        cfg.setConfiguration("RandomText", "Hello World");

        assertThat(cfg.getConfiguration("Integer", Integer.class), is(1));
        assertEquals(1.5F, cfg.getConfiguration("Float", Float.class), 0.01F);
        assertEquals(3.14D, cfg.getConfiguration("Double", Double.class), 0.01D);
        assertThat(cfg.getConfiguration("Byte", Byte.class), is((byte) 0xFF));
        assertThat(cfg.getConfiguration("RandomText", String.class), is("Hello World"));
    }

    @Test
    public void testWholeConfigSerialization() throws IOException {
        File cfgFile = new File("newFolder/config.json");
        Config cfg = new Config(cfgFile);

        cfg.setConfiguration("Integer", -999);
        cfg.setConfiguration("Float", 1.5F);
        cfg.setConfiguration("Double", 3.14D);
        cfg.setConfiguration("Byte", (byte) 0xFF);
        cfg.setConfiguration("RandomText", "Hello");
        cfg.setConfiguration("Integer", 1);
        cfg.setConfiguration("RandomText", "Hello World");

        cfg.writeToDisk();
        cfg = new Config(cfgFile);

        assertThat(cfg.getConfiguration("Integer", Integer.class), is(1));
        assertEquals(1.5F, cfg.getConfiguration("Float", Float.class), 0.01F);
        assertEquals(3.14D, cfg.getConfiguration("Double", Double.class), 0.01D);
        assertThat(cfg.getConfiguration("Byte", Byte.class), is((byte) 0xFF));
        assertThat(cfg.getConfiguration("RandomText", String.class), is("Hello World"));

        cfg.setConfiguration("RandomText", "Bye");
        cfg.setConfiguration("Byte", (byte) 0xAE);

        assertThat(cfg.getConfiguration("RandomText", String.class), is("Bye"));
        assertThat(cfg.getConfiguration("Byte", Byte.class), is((byte) 0xAE));

        cfgFile.delete();
        cfgFile.getParentFile().delete();
    }

    @Test
    public void testSerializationSimple() {
        ConfigValue<String> cvString = new StringConfigValue("Hello World");
        ConfigValue<Integer> cvInteger = new NumberConfigValue<>(1);
        ConfigValue<Double> cvDouble = new NumberConfigValue<>(2.5D);
        ConfigValue<Long> cvLong = new NumberConfigValue<>(300L);

        String jsonString = GsonUtil.instance.gson.toJson(cvString);
        String jsonInteger = GsonUtil.instance.gson.toJson(cvInteger);
        String jsonDouble = GsonUtil.instance.gson.toJson(cvDouble);
        String jsonLong = GsonUtil.instance.gson.toJson(cvLong);

        ConfigValue cvStringP = GsonUtil.instance.gson.fromJson(jsonString, ConfigValue.class);
        ConfigValue cvIntegerP = GsonUtil.instance.gson.fromJson(jsonInteger, ConfigValue.class);
        ConfigValue cvDoubleP = GsonUtil.instance.gson.fromJson(jsonDouble, ConfigValue.class);
        ConfigValue cvLongP = GsonUtil.instance.gson.fromJson(jsonLong, ConfigValue.class);

        assertThat(cvString.getValue(), is(cvStringP.getValue()));
        assertThat(cvInteger.getValue(), is(cvIntegerP.getValue()));
        assertThat(cvDouble.getValue(), is(cvDoubleP.getValue()));
        assertThat(cvLong.getValue(), is(cvLongP.getValue()));
    }

    private Config createDummyConfig() throws IOException {
        File cfgFile = new File("config.json");
        Config cfg = new Config(cfgFile);
        cfgFile.delete();
        return cfg;
    }

    @Test(expected = ConfigValueInvalidTypeException.class)
    public void testConfigExceptionsGetNumberAsString() throws IOException {
        Config cfg = createDummyConfig();

        cfg.setConfiguration("myInt", 10);
        cfg.getConfiguration("myInt", String.class);
    }

    @Test(expected = ConfigValueInvalidTypeException.class)
    public void testConfigExceptionsGetStringAsNumber() throws IOException {
        Config cfg = createDummyConfig();

        cfg.setConfiguration("myString", "Hello World");
        cfg.getConfiguration("myString", Integer.class);
    }

    @Test(expected = ConfigValueInvalidTypeException.class)
    public void testConfigExceptionsGetNumberAsNumber() throws IOException {
        Config cfg = createDummyConfig();

        cfg.setConfiguration("myShort", (short) 10);
        cfg.getConfiguration("myShort", Integer.class);
    }

    @Test(expected = ConfigValueInvalidTypeException.class)
    public void testConfigExceptionsSetNumbers() throws IOException {
        Config cfg = createDummyConfig();

        cfg.setConfiguration("myInt", 10);
        cfg.setConfiguration("myInt", (short) 2);
    }

    @Test(expected = ConfigValueInvalidTypeException.class)
    public void testConfigExceptionsSetStringFirst() throws IOException{
        Config cfg = createDummyConfig();

        cfg.setConfiguration("myString", "Hello World");
        cfg.setConfiguration("myString", 10);
    }

    @Test(expected = ConfigValueInvalidTypeException.class)
    public void testConfigExceptionsSetNumberFirst() throws IOException{
        Config cfg = createDummyConfig();

        cfg.setConfiguration("myString", 10);
        cfg.setConfiguration("myString", "Hello World");
    }

    @Test(expected = JsonParseException.class)
    public void testConfigInvalidClassType() throws IOException {
        new Config(new File("src/test/resources/invconf1.json"));
    }

    @Test(expected = JsonParseException.class)
    public void testConfigInvalidNestedObject() throws IOException {
        new Config(new File("src/test/resources/invconf2.json"));
    }

    @Test(expected = JsonParseException.class)
    public void testConfigInvalidArray() throws IOException {
        new Config(new File("src/test/resources/invconf3.json"));
    }

    @Test(expected = JsonSyntaxException.class)
    public void testConfigInvalidJSON() throws IOException {
        new Config(new File("src/test/resources/invconf4.json"));
    }

    @Test(expected = JsonParseException.class)
    public void testConfigInvalidClass() throws IOException {
        new Config(new File("src/test/resources/invconf5.json"));
    }

}
