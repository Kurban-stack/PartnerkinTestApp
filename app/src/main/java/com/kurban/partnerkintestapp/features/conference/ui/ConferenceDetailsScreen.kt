package com.kurban.partnerkintestapp.features.conference.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.kurban.partnerkintestapp.common.ui.PrimaryButton
import com.kurban.partnerkintestapp.common.ui.Toolbar
import com.kurban.partnerkintestapp.features.conference.ui.state.ConferenceState
import com.kurban.partnerkintestapp.ui.theme.PartnerkinTheme
import com.kurban.partnerkintestapp.ui.theme.spacing
import org.koin.androidx.compose.koinViewModel
import java.util.Date

@Composable
fun ConferenceScreen(onNavigateBack: () -> Unit) {
    val viewModel: ConferenceDetailsViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (state) {
        is ConferenceState.Content -> {
            val content = (state as ConferenceState.Content).conferense
            ConferenceScreenContent(content, onNavigateBack)
        }

        is ConferenceState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }

        is ConferenceState.Error -> {
            Text(
                text = stringResource(R.string.conferenсes_screen_error),
                color = Color.Red,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
    }
}

@Composable
private fun ConferenceScreenContent(
    conference: Conference,
    onNavigateBack: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        Toolbar(
            modifier = Modifier.fillMaxWidth(),
            leftIcon = {
                Icon(
                    modifier = Modifier.clickable { onNavigateBack() },
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = null
                )
            }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = MaterialTheme.spacing.large)
        ) {
            Text(
                modifier = Modifier.padding(
                    top = MaterialTheme.spacing.large,
                    bottom = MaterialTheme.spacing.medium
                ),
                text = stringResource(R.string.conferenсe_screen_title),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                modifier = Modifier.padding(
                    bottom = MaterialTheme.spacing.extraLarge,
                ),
                text = conference.name,
                style = MaterialTheme.typography.titleLarge
            )

            Image(
                painter = rememberAsyncImagePainter(conference.imageUrl),
                contentScale = ContentScale.Crop,
                contentDescription = conference.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = MaterialTheme.spacing.large)
                    .height(219.dp)
                    .clip(MaterialTheme.shapes.medium)
            )

            Row(
                Modifier.padding(bottom = MaterialTheme.spacing.large),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                conference.categories.forEach {
                    Chip(title = {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 10.dp, vertical = 6.dp),
                            text = it,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }, backgroundColor = MaterialTheme.colorScheme.primary)
                }
            }

            Row(
                modifier = Modifier.padding(bottom = MaterialTheme.spacing.medium),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_schedule),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )

                Text(
                    text = "22 октября 2024, 2 дня", //TODO
                    style = MaterialTheme.typography.headlineMedium,
                )
            }

            Row(
                modifier = Modifier.padding(bottom = MaterialTheme.spacing.extraLarge),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_location_blue),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )

                Text(
                    text = "${conference.city}, ${conference.country}",
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = MaterialTheme.spacing.extraLarge),
                title = stringResource(R.string.conferenсe_screen_button_title),
                onClick = {}
            )

            Text(
                modifier = Modifier
                    .padding(bottom = MaterialTheme.spacing.extraLarge),
                text = stringResource(R.string.conferenсe_screen_events_title),
                style = MaterialTheme.typography.titleMedium
            )

            ConferenceRatingList()

            Spacer(Modifier.height(48.dp))

            Text(
                modifier = Modifier,
                text = stringResource(R.string.conferenсe_screen_about_event),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(MaterialTheme.spacing.large))

            conference.about?.let {
                Text(
                    modifier = Modifier,
                    text = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun ConferenceRatingList() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Chip(
                    title = {
                        Text(
                            modifier = Modifier
                                .padding(5.dp),
                            text = "New",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    },
                    backgroundColor = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "ЛАС-ВЕГАС ЯНВ ‘24",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Icon(
                painter = painterResource(R.drawable.ic_forward),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = MaterialTheme.colorScheme.primary)
        )

        RatingRow(
            title = "ЛАС-ВЕГАС ЯНВ ‘24",
            rating = "5"
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = MaterialTheme.colorScheme.primary)
        )

        RatingRow(
            title = "ЛАС-ВЕГАС ЯНВ ‘24",
            rating = "8.3"
        )
    }
}

@Composable
private fun RatingRow(
    title: String,
    rating: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.surface
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_star),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .padding(end = 2.dp)
            )

            Text(
                text = rating,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black,
                modifier = Modifier.padding(end = 6.dp)
            )

            Icon(
                painter = painterResource(R.drawable.ic_forward),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.surface,
            )
        }
    }
}

@Preview
@Composable
private fun ConferenceScreenPreview() {
    PartnerkinTheme {
        ConferenceScreenContent(
            conference = Conference(
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
                about = "EDCON 2024 представляет собой событие, которое объединяет спикеров высшего уровня и предлагает релевантный контент для участников. Это не просто конференция — это возможность погрузиться в мир Ethereum и встретиться с представителями лучшего сообщества в этой сфере."
            )
        ) { }
    }
}