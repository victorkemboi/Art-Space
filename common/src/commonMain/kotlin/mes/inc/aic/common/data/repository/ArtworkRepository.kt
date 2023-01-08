package mes.inc.aic.common.data.repository

import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import mes.inc.aic.common.data.cache.ArtworkDao
import mes.inc.aic.common.data.model.Artwork
import mes.inc.aic.common.data.model.Reel
import mes.inc.aic.common.data.service.aic.ArtInstituteOfChicagoService

interface ArtworkRepository {
    suspend fun fetchArtworks(query: String? = null): Flow<List<Artwork>>
    fun fetchArtworkReels(): Flow<List<Reel>>
}

class ArtworkRepositoryImpl(
    private val artworkDao: ArtworkDao,
    private val artInstituteOfChicagoService: ArtInstituteOfChicagoService
) : ArtworkRepository {

    override suspend fun fetchArtworks(query: String?): Flow<List<Artwork>> {
        artInstituteOfChicagoService.fetchArtworks(query = query)
        Logger.i("Fetching Artworks")
        return artworkDao.fetchArtworks(query)
    }

    override fun fetchArtworkReels(): Flow<List<Reel>> = flowOf(emptyList())
}