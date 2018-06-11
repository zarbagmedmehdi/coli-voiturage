package WebService.util;

import com.application.zarbagaskazay.colivoiturage.ActivityHelper;

public class UrlClass {
    /////////////192.168.43.177
    /////////////192.168.1.10
  private final String  apiKeyDistance="AIzaSyDIlEpPQKYKVwjarpi_xJnEXNirg-qkSa4";

  private final String   apiKeyGEeo="AIzaSyC0OTIef6-_7EgaFTXEL1wFgyTpGmFVSsk";
  private final String ipGoogleMap="https://maps.googleapis.com/maps/api/";
  public  final String distanceUrl=ipGoogleMap+"distancematrix/json?";

    ActivityHelper helper = new ActivityHelper();
   // public final String ip = "http://192.168.1.4/";
    //public final String ip="http://192.168.1.10/";
    private final String ip = "http://192.168.43.177/";

    public final String urlInscription = ip + "api/api/all/inscription";
    public final String urlAddVoiture = ip + "api/api/ajout/voiture";
    public final String urlConnexion = ip + "api/api/client/connexion";
    //////// chuf had lien li t7t bdlu  mli tzid connexion oula inscription  start activity hint fih id =1 pardefaut
    public final String urlConducteurProfile = ip + "api/api/find/getInfoConducteur?id=1";
    public final String urlcc = ip + "api/api/cc";


    public final String urlAddAlerte = ip + "api/api/ajout/alerte";
    public final String urlAllAlerte = ip + "api/api/all/alerte";
    public final String urlFindAlertes = ip + "api/api/chercher/alerte";

    public final String urlAddNewTrajet = ip + "api/api/lancer/trajet";
    public final String urlExpediteurProfile = ip + "api/api/find/getInfoExpediteur?id=8";
    public final String urlFindAdresse = ip + "api/api/find/adresseVille";  //?nomVille=MARRAKECH
    public final String urlMesAlerte=ip+"api/api/find/mesAlertes?id_e=8";
}


///https://maps.googleapis.com/maps/api/distancematrix/json?origins=marrakech+morocco&destinations=BENIMELLAL+morocco&key=AIzaSyDIlEpPQKYKVwjarpi_xJnEXNirg-qkSa4
//https://maps.googleapis.com/maps/api/geocode/json?address=casablanca+center+morocco&key=AIzaSyC0OTIef6-_7EgaFTXEL1wFgyTpGmFVSsk