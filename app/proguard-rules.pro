# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
-dontwarn org.conscrypt.Conscrypt$Version
-dontwarn org.conscrypt.Conscrypt
-dontwarn org.conscrypt.ConscryptHostnameVerifier
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE


# Keep presenataion package when runing the release version
-keep public class com.dev0ko.ewelp.**{*;}

# Keep retrofit classes when runing the release version
-keep class retrofit2.** { *; }

-if class androidx.credentials.CredentialManager
-keep class androidx.credentials.playservices.** {
  *;
}

-keep class com.facebook.** { *; }
-dontwarn com.facebook.**

