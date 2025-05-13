/**
* 💡 Jetpack Compose Card with Dark/Light Theme Support
**/

/**
* 🧱 User.kt — Data Model
**/
data class User(
    val name: String,
    val profession: String,
    val imageUrl: String
)


/**
* 🎨 Theme.kt — Compose Theme (Light + Dark)
* Use default MaterialTheme support:
**/
@Composable
fun MyAppTheme(content: @Composable () -> Unit) {
    val darkTheme = isSystemInDarkTheme()

    MaterialTheme(
        colorScheme = if (darkTheme) darkColorScheme() else lightColorScheme(),
        typography = Typography(),
        content = content
    )
}


/**
* 📇 UserCard.kt — Card with Theme Adaptation
**/
@Composable
fun UserCard(
    user: User,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        border = BorderStroke(1.dp, colors.primary.copy(alpha = 0.3f)),
        colors = CardDefaults.cardColors(
            containerColor = colors.surface,
            contentColor = colors.onSurface
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = user.imageUrl,
                contentDescription = user.name,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = colors.onSurface
                )
                Text(
                    text = user.profession,
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.onSurfaceVariant
                )
            }
        }
    }
}



/**
* 📱 MainScreen.kt — Preview with Dark/Light
**/
@Composable
fun MainScreen() {
    val sampleUser = User(
        name = "John Doe",
        profession = "Android Developer",
        imageUrl = "https://picsum.photos/200"
    )

    MyAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            UserCard(user = sampleUser) {
                Log.d("UserCard", "Card clicked!")
            }
        }
    }
}

