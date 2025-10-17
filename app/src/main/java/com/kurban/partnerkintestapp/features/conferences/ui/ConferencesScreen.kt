package com.kurban.partnerkintestapp.features.conferences.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.kurban.partnerkintestapp.R
import com.kurban.partnerkintestapp.common.domain.model.Conference
import com.kurban.partnerkintestapp.common.domain.model.Status
import com.kurban.partnerkintestapp.common.ui.Chip
import com.kurban.partnerkintestapp.common.ui.Toolbar
import com.kurban.partnerkintestapp.features.conferences.ui.state.ConferencesState
import com.kurban.partnerkintestapp.ui.theme.PartnerkinTheme
import com.kurban.partnerkintestapp.ui.theme.spacing
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ConferencesScreen(onNavigateToSecond: () -> Unit) {
    val viewModel: ConferencesViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (state) {
        is ConferencesState.Content -> {
            val content = (state as ConferencesState.Content).conferenses
            ConferencesScreenContent(onNavigateToSecond, content)
        }

        is ConferencesState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }

        is ConferencesState.Error -> {
            Text(
                text = stringResource(R.string.conferenсes_screen_error),
                color = MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
    }
}

@Composable
private fun ConferencesScreenContent(
    onNavigateToSecond: () -> Unit,
    conferences: Map<String, List<Conference>>
) {
    Column {
        Toolbar(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.conferenсes_screen_toolbar_title),
            rightIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_customer_support),
                    contentDescription = null
                )
            }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            conferences.forEach { (monthTitle, conferenceList) ->
                item {
                    Text(
                        text = monthTitle.replaceFirstChar { it.uppercaseChar() },
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .padding(
                                horizontal = MaterialTheme.spacing.large,
                                vertical = MaterialTheme.spacing.extraLarge
                            )
                    )
                }

                items(conferenceList) { conference ->
                    ConferenceCard(conference = conference, onNavigateToSecond = onNavigateToSecond)
                }
            }
        }
    }
}

@SuppressLint("InvalidColorHexValue")
@Composable
private fun ConferenceCard(
    conference: Conference,
    onNavigateToSecond: () -> Unit,
) {
    val isCancelled = conference.status == Status.CANCELED
    val backgroundColor = if (isCancelled) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavigateToSecond() }
            .clip(MaterialTheme.shapes.medium),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = MaterialTheme.spacing.large)
                .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
        ) {
            if (isCancelled) StatusLabel(Modifier.padding(top = 10.dp))

            Text(
                modifier = Modifier.padding(top = MaterialTheme.spacing.extraLarge, bottom = 20.dp),
                text = conference.name,
                style = MaterialTheme.typography.titleLarge
            )

            EventCard(
                conf = conference,
                isCancelled = isCancelled
            )

            Row(
                Modifier.padding(
                    bottom = MaterialTheme.spacing.large
                ),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
            ) {
                conference.categories.forEach {
                    Chip(backgroundColor = MaterialTheme.colorScheme.background, title = {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 10.dp, vertical = 6.dp),
                            text = it,
                            style = MaterialTheme.typography.labelSmall
                        )
                    })
                }
            }

            Row(modifier = Modifier.padding(bottom = MaterialTheme.spacing.extraLarge)) {
                Icon(
                    painter = painterResource(R.drawable.ic_location),
                    contentDescription = null
                )

                Text(
                    modifier = Modifier.padding(start = MaterialTheme.spacing.medium),
                    text = "${conference.city}, ${conference.country}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun StatusLabel(
    modifier: Modifier = Modifier
) {
    val borderColor = MaterialTheme.colorScheme.outlineVariant

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .wrapContentSize()
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(50)
            )

            .padding(horizontal = 10.dp, vertical = MaterialTheme.spacing.medium)
            .background(color = MaterialTheme.colorScheme.error, shape = RoundedCornerShape(50)),
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_lightning_bold), // Замените на свою иконку, если нужно
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
        Text(
            text = stringResource(R.string.conferenсes_screen_canceled_label),
            color = borderColor,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun EventCard(
    modifier: Modifier = Modifier,
    conf: Conference,
    isCancelled: Boolean
) {
    val dayFormat = remember { SimpleDateFormat("dd", Locale.getDefault()) }
    val monthFormat = remember { SimpleDateFormat("MMM", Locale.ENGLISH) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = MaterialTheme.spacing.extraLarge)
            .background(if (isCancelled) MaterialTheme.colorScheme.onErrorContainer else MaterialTheme.colorScheme.onPrimaryContainer)
            .clip(MaterialTheme.shapes.medium)
    ) {

        Image(
            painter = rememberAsyncImagePainter(conf.imageUrl),
            contentScale = ContentScale.Crop,
            contentDescription = conf.name,
            modifier = Modifier
                .height(104.dp)
                .weight(0.45f)
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.55f)
        ) {
            Row(modifier = Modifier.align(Alignment.Center)) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = dayFormat.format(conf.startDate),
                        style = MaterialTheme.typography.displayLarge
                    )
                    Text(
                        text = monthFormat.format(conf.startDate).uppercase()
                    )
                }

                Text(
                    text = " - ",
                    style = MaterialTheme.typography.displayLarge
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = dayFormat.format(conf.endDate),
                        style = MaterialTheme.typography.displayLarge
                    )
                    Text(
                        text = monthFormat.format(conf.endDate).uppercase()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ConferencesScreenPreview() {
    PartnerkinTheme {
        val fakeConferences = mapOf(
            "Июль, 2024" to listOf(
                Conference(
                    id = 1,
                    name = "Tech Future Summit 2025",
                    status = Status.CANCELED,
                    statusTitle = "Идет регистрация",
                    imageUrl = "https://example.com/images/techfuture.jpg",
                    startDate = Date(2025 - 1900, 5, 12), // 2025-06-12
                    endDate = Date(2025 - 1900, 5, 14),   // 2025-06-14
                    oneDay = 0,
                    country = "Россия",
                    city = "Москва",
                    categories = listOf("AI", "Blockchain", "Cybersecurity"),
                    about = null,
                )
            ),
            "Август, 2024" to listOf(
                Conference(
                    id = 2,
                    name = "Global Design Week 2025",
                    status = Status.PUBLISH,
                    statusTitle = "Скоро начнется",
                    imageUrl = "https://example.com/images/designweek.jpg",
                    startDate = Date(2025 - 1900, 8, 3), // 2025-09-03
                    endDate = Date(2025 - 1900, 8, 3),
                    oneDay = 1,
                    country = "Великобритания",
                    city = "Лондон",
                    categories = listOf("UX/UI", "Product Design", "Branding"),
                    about = null,
                )
            )
        )

        ConferencesScreenContent(conferences = fakeConferences, onNavigateToSecond = {})
    }
}
