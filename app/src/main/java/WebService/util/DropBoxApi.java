package WebService.util;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.dropbox.core.v1.DbxEntry;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DropBoxApi {
    private static final String ACCESS_TOKEN = "_xC18cxkB7AAAAAAAAAABoEeIYfeFdoMzKcIHayC8bkBh9y991COjmeZfUFWPvH5";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void main(String args[]) throws DbxException {

        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
      FullAccount account = client.users().getCurrentAccount();
       System.out.println(account.getName().getDisplayName());

        listFolder( client);
       // uploadFile("E:/colivoiturage/app/src/main/res/layout/test.xml", "/cc/" + "test.xml",client);
        readFile("C:\\Users\\MY.MEHDI\\Desktop" , client,  "/cc/test.xml") ;
    }




    public static void listFolder(DbxClientV2 client)
    {
        try
        {
            // Get files and folder metadata from Dropbox root directory
            ListFolderResult result = client.files().listFolder("");
            while (true) {
                for (Metadata metadata : result.getEntries()) {
                    System.out.println(metadata.getPathLower());
                }

                if (!result.getHasMore()) {
                    break;
                }

                result = client.files().listFolderContinue(result.getCursor());
                System.out.println(result);
            }
        }
        catch (DbxException dbxe)
        {
            dbxe.printStackTrace();
        }
    }
    public  static void uploadFile(String path, String foldername,DbxClientV2 client)
    {
        // Upload "test.txt" to Dropbox
        try {
            InputStream in = new FileInputStream(path);
            FileMetadata metadata = client.files().uploadBuilder(foldername).uploadAndFinish(in);
            System.out.println("succes");

        }
        catch (FileNotFoundException fne)
        {                System.out.println("file not found");

            fne.printStackTrace();
        }
        catch (IOException ioe)
        {System.out.println("dd");
            ioe.printStackTrace();
        }
        catch (DbxException dbxe)
        {System.out.println("ee");
            dbxe.printStackTrace();
        }
    }


    public  static void readFile(String foldername,DbxClientV2 client, String filename)
    {
        try
        {
            //output file for download --> storage location on local system to download file
            FileOutputStream downloadFile = new FileOutputStream(  filename);
            try {
                FileMetadata metadata = client.files().downloadBuilder(foldername).download(downloadFile);
            } finally
            {
                downloadFile.close();
            }
        }
        //exception handled
        catch (DbxException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteFile(String path,DbxClientV2 client)
    {
        try
        {
            Metadata metadata = client.files().delete(path);
        }
        catch (DbxException dbxe)
        {
            dbxe.printStackTrace();
        }
    }

    }


