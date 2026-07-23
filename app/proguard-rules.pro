# kotlinx.serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.**
-keepclassmembers class **$$serializer { *; }
-keepclassmembers @kotlinx.serialization.Serializable class ** {
    *** Companion;
    *** INSTANCE;
    kotlinx.serialization.KSerializer serializer(...);
}

# Keep the @Serializable lectionary models and their generated serializers intact
# so JSON loading survives R8 full-mode shrinking.
-keep @kotlinx.serialization.Serializable class com.comfortcross.liturgy.data.model.** { *; }
-keep,includedescriptorclasses class com.comfortcross.liturgy.data.model.**$$serializer { *; }
