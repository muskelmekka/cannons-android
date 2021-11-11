package dev.muskelmekka.cannons.app.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FirebaseFirestoreModule {
  @Provides
  fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()
}
