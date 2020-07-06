package com.nm.data.repository

import com.nm.commons.net.data.RetrofitBuild.makeService
import com.nm.data.model.CardSchema
import com.nm.data.model.CardTypesSchema
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class CardTypeRemoteDataSourcImplTest {

    private var mockWebServer: MockWebServer? = null

    private lateinit var remoteDataSource: CardTypeRemoteDataSource

    @Before
    fun setupTest() {
        startMockWebServer()

        remoteDataSource = CardTypeRemoteDataSourcImpl(makeService(mockWebServer!!.url("/")))
    }

    @Test
    fun remoteDataSourceSuccess() {
        mockWebServer!!.enqueue(
            MockResponse().apply {
                val response = createWSResponse()
                setResponseCode(HTTP_OK).setBody(response)
            }
        )

        val testObserver = remoteDataSource.getCardTypesRemote().test()

        testObserver.assertValue(createCardTypeSchema())
    }

    private fun createWSResponse(): String {
        return StringBuilder().append(
            "{\n" +
                    "\t\"patch\": \"17.4.1.51510\",\n" +
                    "\t\"classes\": [\n" +
                    "\t\t\"Death Knight\",\n" +
                    "\t\t\"Druid\"\n" +
                    "\t],\n" +
                    "\t\"sets\": [\n" +
                    "\t\t\"Basic\",\n" +
                    "\t\t\"Classic\"\n" +
                    "\t],\n" +
                    "\t\"standard\": [\n" +
                    "\t\t\"Rise of Shadows\",\n" +
                    "\t\t\"Saviors of Uldum\"\n" +
                    "\t],\n" +
                    "\t\"wild\": [\n" +
                    "\t\t\"Hall of Fame\",\n" +
                    "\t\t\"Naxxramas\"\n" +
                    "\t],\n" +
                    "\t\"types\": [\n" +
                    "\t\t\"Hero\",\n" +
                    "\t\t\"Minion\"\n" +
                    "\t],\n" +
                    "\t\"factions\": [\n" +
                    "\t\t\"Horde\",\n" +
                    "\t\t\"Alliance\"\n" +
                    "\t],\n" +
                    "\t\"qualities\": [\n" +
                    "\t\t\"Epic\",\n" +
                    "\t\t\"Legendary\"\n" +
                    "\t],\n" +
                    "\t\"races\": [\n" +
                    "\t\t\"Murloc\",\n" +
                    "\t\t\"Beast\"\n" +
                    "\t],\n" +
                    "\t\"locales\": [\n" +
                    "\t\t\"koKR\",\n" +
                    "\t\t\"ptBR\"\n" +
                    "\t]\n" +
                    "}"
        ).toString()
    }

    private fun createCardTypeSchema(): CardTypesSchema {
        return CardTypesSchema(
            classes = listOf("Death Knight", "Druid"),
            factions = listOf("Horde", "Alliance"),
            locales = listOf("koKR", "ptBR"),
            qualities = listOf("Epic", "Legendary"),
            races = listOf("Murloc", "Beast"),
            sets = listOf("Basic", "Classic"),
            standard = listOf("Rise of Shadows", "Saviors of Uldum"),
            types = listOf("Hero", "Minion"),
            wild = listOf("Hall of Fame", "Naxxramas")
        )
    }

    @Test
    fun remoteCardListDataSourceSuccess() {
        mockWebServer!!.enqueue(
            MockResponse().apply {
                val response = createCardListWSResponse()
                setResponseCode(HTTP_OK).setBody(response)
            }
        )

        val testObserver = remoteDataSource.getCardSearch("races", "Dragon").test()

        testObserver.assertValue(createCardList())
    }

    private fun createCardListWSResponse(): String {
        return StringBuilder().append(
            "[\n" +
                    "    {\n" +
                    "        \"cardId\": \"BRM_022t\",\n" +
                    "        \"dbfId\": \"2436\",\n" +
                    "        \"name\": \"Black Whelp\",\n" +
                    "        \"cardSet\": \"Blackrock Mountain\",\n" +
                    "        \"type\": \"Minion\",\n" +
                    "        \"health\": 1,\n" +
                    "        \"img\": \"http://wow.zamimg.com/images/hearthstone/cards/enus/original/BRM_022t.png\",\n" +
                    "        \"imgGold\": \"http://wow.zamimg.com/images/hearthstone/cards/enus/animated/BRM_022t_premium.gif\",\n" +
                    "        \"locale\": \"enUS\"\n" +
                    "    }\n" +
                    "]"
        ).toString()
    }

    private fun createCardList(): List<CardSchema> {
        return arrayListOf(
            CardSchema(
                cardId = "BRM_022t",
                cardSet = "Blackrock Mountain",
                dbfId = "2436",
                health = 1,
                img = "http://wow.zamimg.com/images/hearthstone/cards/enus/original/BRM_022t.png",
                imgGold = "http://wow.zamimg.com/images/hearthstone/cards/enus/animated/BRM_022t_premium.gif",
                locale = "enUS",
                name = "Black Whelp",
                type = "Minion"
            )
        )

    }

    @After
    fun tearDown() {
        stopMockWebServer()
    }

    private fun startMockWebServer() {
        if (mockWebServer == null) {
            mockWebServer = MockWebServer()
            mockWebServer?.start(MOCK_WEB_SERVER_PORT)
        }
    }

    private fun stopMockWebServer() {
        if (mockWebServer != null) {
            mockWebServer?.shutdown()
            mockWebServer = null
        }
    }

    companion object {
        const val MOCK_WEB_SERVER_PORT = 9091

        const val HTTP_OK = 200
        const val HTTP_ERROR = 400
    }

}