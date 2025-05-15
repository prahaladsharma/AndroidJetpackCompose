/**
* ✅ Assist Chip – Example
**/
@Composable
fun AssistChipSample() {
    AssistChip(
        onClick = { Log.d("Chip", "Clicked!") },
        label = { Text("Assist Chip") },
        leadingIcon = { Icon(Icons.Default.Favorite, contentDescription = null) }
    )
}


/**
* ✅ Filter Chip – Example
**/
@Composable
fun FilterChipSample() {
    var selected by remember { mutableStateOf(false) }

    FilterChip(
        selected = selected,
        onClick = { selected = !selected },
        label = { Text("Filter Chip") },
        leadingIcon = {
            if (selected) Icon(Icons.Default.Check, contentDescription = null)
        }
    )
}


/**
* ✅ Input Chip – Example
**/
@Composable
fun InputChipSample() {
    var visible by remember { mutableStateOf(true) }

    if (visible) {
        InputChip(
            selected = false,
            onClick = { /* Do something */ },
            onDelete = { visible = false },
            label = { Text("Input Chip") },
            avatar = { Icon(Icons.Default.Person, contentDescription = null) }
        )
    }
}


/**
* 
**/
@Composable
fun InputChipSample() {
    var visible by remember { mutableStateOf(true) }

    if (visible) {
        InputChip(
            selected = false,
            onClick = { /* Do something */ },
            onDelete = { visible = false },
            label = { Text("Input Chip") },
            avatar = { Icon(Icons.Default.Person, contentDescription = null) }
        )
    }
}


/**
* ✅ Suggestion Chip – Example
**/
@Composable
fun SuggestionChipSample() {
    SuggestionChip(
        onClick = { /* Accept suggestion */ },
        label = { Text("Compose") }
    )
}


/**
* 🎨 Customizing Chip Style
* You can use parameters like:
*   colors: Customize background, text, icon color
*   shape: Roundness via RoundedCornerShape
*   elevation: Shadow/elevation
*   icon: Leading icon
*   label: Text shown inside chip
**/
FilterChip(
    selected = true,
    onClick = {},
    label = { Text("Custom") },
    shape = RoundedCornerShape(16.dp),
    colors = FilterChipDefaults.filterChipColors(
        selectedContainerColor = Color.Green,
        selectedLabelColor = Color.White
    )
)


/**
* 🔄 State Management in Chips
* Chips are interactive. Always manage their state using remember or pass state from ViewModel (MVVM):
**/
    val selectedChips = remember { mutableStateListOf<String>() }


/**
* 🧠 Best Practices
**/
| Best Practice             | Why?                                |
| ------------------------- | ----------------------------------- |
| Use concise labels        | Chips are compact                   |
| Use icons sparingly       | Only if it adds meaning             |
| Combine chips with rows   | For dynamic list of options         |
| Ensure accessible content | Use meaningful content descriptions |


/**
* 🧪 Preview All Chips
**/
@Preview(showBackground = true)
@Composable
fun PreviewAllChips() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        AssistChipSample()
        FilterChipSample()
        InputChipSample()
        SuggestionChipSample()
    }
}


/**
* 🔌 Bonus: Chip Row with LazyRow
**/
@Composable
fun ChipRowExample() {
    val tags = listOf("Jetpack", "Compose", "Kotlin", "UI")

    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(tags) { tag ->
            FilterChip(
                selected = false,
                onClick = { /* handle selection */ },
                label = { Text(tag) }
            )
        }
    }
}

