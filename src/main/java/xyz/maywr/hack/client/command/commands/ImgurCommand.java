package xyz.maywr.hack.client.command.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import xyz.maywr.hack.api.util.MessageUtil;
import xyz.maywr.hack.client.command.Command;
import xyz.maywr.hack.client.command.CommandManifest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;

@CommandManifest(label = "imgur")
public class ImgurCommand extends Command {

    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

    @Override
    public void execute(String[] args) {
        try {
            ResourceLocation resourceLocation = mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem().getRegistryName();
            InputStream inputStream = mc.getResourceManager().getResource(new ResourceLocation(resourceLocation.getNamespace() + ":textures/items/" + resourceLocation.getPath() + ".png")).getInputStream();
            BufferedImage image = ImageIO.read(inputStream);
            MessageUtil.sendClientMessage(imgurUpload(image), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String imgurUpload(BufferedImage bufferedImage) {
        String result = "no";
        try{
            URL url = new URL("https://api.imgur.com/3/image");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Client-ID bfea9c11835d95c");
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.connect();
            ImageIO.write(bufferedImage, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            String encoded = Base64.getEncoder().encodeToString(imageInByte);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            String data = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(encoded, "UTF-8");
            wr.write(data);
            wr.flush();
            BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            StringBuilder stb = new StringBuilder();
            while ((line = rd.readLine()) != null){
                stb.append(line).append("\n");
            }
            con.getResponseCode();
            wr.close();
            rd.close();
            JsonObject jsonObject = new JsonParser().parse(stb.toString()).getAsJsonObject();
            result = jsonObject.get("data").getAsJsonObject().get("link").getAsString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
