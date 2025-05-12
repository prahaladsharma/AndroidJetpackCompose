package com.example.data.repository.topnews

import app.cash.turbine.test
import com.example.data.api.NetworkApiService
import com.example.data.model.ArticleDTO
import com.example.data.model.SourceDTO
import com.example.data.model.TopHeadlinesResponseDTO
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlinx.coroutines.test.runTest
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class TopHeadlineRepositoryImplTest{

    @Mock
    private lateinit var mockApiService: NetworkApiService

    private lateinit var topHeadlineRepository: TopHeadlineRepository

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        topHeadlineRepository = TopHeadlineRepositoryImpl(mockApiService)
    }

    @Test
    fun `test getTopHeadlines emits list of articles`() = runTest {
        // Given: Mock API response with a list of ArticleDTOs
        val mockSource = SourceDTO("sourceId", "sourceName")
        val mockArticle = ArticleDTO(
            title = "Sample Title",
            description = "Sample Description",
            url = "https://sample.url",
            imageUrl = "https://sample.url/image.jpg",
            source = mockSource
        )
        val mockResponse = TopHeadlinesResponseDTO("status", 0, listOf(mockArticle))

        // When: Mocking the service to return the mock response
        whenever(mockApiService.getTopHeadlines("us")).thenReturn(mockResponse)

        // Then: Calling the repository and testing the flow
        topHeadlineRepository.getTopHeadlines("us").test {
            // Verify if the first emitted value matches the expected response
            val articles = awaitItem()
            assertEquals(1, articles.size)
            assertEquals("Sample Title", articles[0].title)
            assertEquals("Sample Description", articles[0].description)
            assertEquals("https://sample.url", articles[0].url)
            assertEquals("https://sample.url/image.jpg", articles[0].imageUrl)
            assertEquals("sourceName", articles[0].source.name)

            awaitComplete()
        }

        // Verify that the API service method was called exactly once
        verify(mockApiService, times(1)).getTopHeadlines("us")
    }

}