/**
* 🧩 Data Model
**/
data class User(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String
)


/**
* 🚀 Navigation Setup
**/
// Step-1
// Add Navigation Dependency(In build.gradle (app)):
//    implementation("androidx.navigation:navigation-compose:2.7.5")

// Step 2: Navigation Destinations
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{userId}") {
        fun createRoute(userId: Int) = "detail/$userId"
    }
}

/**
* 🧱 Main NavHost Setup
**/
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            DetailScreen(userId)
        }
    }
}


/**
* 🏠 Home Screen with Card
**/
@Composable
fun HomeScreen(navController: NavHostController) {
    val users = listOf(
        User(1, "Prahalad Sharma", "Android Developer", "https://picsum.photos/200"),
        User(2, "Jane Smith", "UI Designer", "https://picsum.photos/201"),
    )

    LazyColumn {
        items(users) { user ->
            UserCard(user = user,
                onClick = { navController.navigate(Screen.Detail.createRoute(user.id)) }
            )
        }
    }
}

/**
* 📇 UserCard Composable
**/
@Composable
fun UserCard(
    user: User,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = user.imageUrl,
                contentDescription = user.name,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = user.name, style = MaterialTheme.typography.titleMedium)
                Text(text = user.description, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}


/**
* 📄 DetailScreen
**/
@Composable
fun DetailScreen(userId: Int) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Detail of user with ID: $userId", style = MaterialTheme.typography.headlineMedium)
    }
}


/**
* ✅ Preview Setup in MainActivity.kt
**/
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppNavigation()
            }
        }
    }
}



