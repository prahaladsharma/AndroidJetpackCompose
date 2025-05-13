/**
* 🔹 What is a Checkbox in Jetpack Compose?
* A Checkbox is a UI element that allows the user to make a binary choice — checked (true) or unchecked (false).
* Jetpack Compose provides a highly customizable Checkbox composable function for this.
**/


/**
* 📦 Default Checkbox Signature
**/
@Composable
fun Checkbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: CheckboxColors = CheckboxDefaults.colors()
)

/**
* 🔸 Key Parameters
**/
| Parameter           | Description                                            |
| ------------------- | ------------------------------------------------------ |
| `checked`           | Boolean state of the checkbox.                         |
| `onCheckedChange`   | Callback triggered when the user toggles the checkbox. |
| `enabled`           | Enables/disables user interaction.                     |
| `colors`            | Customizes the appearance.                             |
| `interactionSource` | Handles gestures and interactions.                     |


/**
* 🚀 Basic Checkbox Example
**/
@Composable
fun BasicCheckboxSample() {
    var checked by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = if (checked) "Checked" else "Unchecked")
    }
}


/**
* 🎨 Customizing Checkbox Colors
**/
Checkbox(
    checked = checked,
    onCheckedChange = { checked = it },
    colors = CheckboxDefaults.colors(
        checkedColor = Color.Green,
        uncheckedColor = Color.Gray,
        checkmarkColor = Color.White,
        disabledColor = Color.LightGray
    )
)


/**
* 🧩 Adding Label/Text with Checkbox.
* Use Row to create a labeled checkbox.
* ⚠️ Use onCheckedChange = null if handling clicks from the parent.
**/
@Composable
fun LabeledCheckbox() {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable { isChecked = !isChecked }
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = null // handled by Row clickable
        )
        Text(text = "I agree to the Terms and Conditions")
    }
}


/**
* 🚫 Disabled Checkbox
**/
Checkbox(
    checked = true,
    onCheckedChange = null,
    enabled = false
)


/**
* 💬 Checkbox with State Hoisting
* You can make reusable components with hoisted state.
**/
@Composable
fun MyCheckboxItem(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Text(text = label)
    }
}


/**
* 🧠 Accessibility Tip
* Always include a label next to a Checkbox. It's important for screen readers and UX clarity.
**/

/**
* 🔄 Checkbox with List Items (Dynamic List)
**/
@Composable
fun CheckboxList() {
    val options = listOf("Kotlin", "Java", "Python")
    val selectedOptions = remember { mutableStateListOf<String>() }

    Column {
        options.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        if (selectedOptions.contains(item)) {
                            selectedOptions.remove(item)
                        } else {
                            selectedOptions.add(item)
                        }
                    }
                    .padding(8.dp)
            ) {
                Checkbox(
                    checked = selectedOptions.contains(item),
                    onCheckedChange = null
                )
                Text(text = item)
            }
        }
    }
}

/**
* 📱 Preview
**/
@Preview(showBackground = true)
@Composable
fun PreviewCheckboxSample() {
    BasicCheckboxSample()
}


/**
* Advanced example
**/
@Composable
fun CheckboxParentExample() {
    // Initialize states for the child checkboxes
    val childCheckedStates = remember { mutableStateListOf(false, false, false) }

    // Compute the parent state based on children's states
    val parentState = when {
        childCheckedStates.all { it } -> ToggleableState.On
        childCheckedStates.none { it } -> ToggleableState.Off
        else -> ToggleableState.Indeterminate
    }

    Column {
        // Parent TriStateCheckbox
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Select all")
            TriStateCheckbox(
                state = parentState,
                onClick = {
                    // Determine new state based on current state
                    val newState = parentState != ToggleableState.On
                    childCheckedStates.forEachIndexed { index, _ ->
                        childCheckedStates[index] = newState
                    }
                }
            )
        }

        // Child Checkboxes
        childCheckedStates.forEachIndexed { index, checked ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Option ${index + 1}")
                Checkbox(
                    checked = checked,
                    onCheckedChange = { isChecked ->
                        // Update the individual child state
                        childCheckedStates[index] = isChecked
                    }
                )
            }
        }
    }

    if (childCheckedStates.all { it }) {
        Text("All options selected")
    }
}

/**
* Explanation
* The following are several points you should note from this example:
*
*  State management:
*   childCheckedStates: A list of booleans using mutableStateOf() to track the checked state of each child checkbox.
*   parentState: A ToggleableState whose value derives from the child checkboxes' states.
*
*  UI components:
*   TriStateCheckbox: Is necessary for the parent checkbox as it has a state param that lets you set it to indeterminate.
*   Checkbox: Used for each child checkbox with its state linked to the corresponding element in childCheckedStates.
*   Text: Displays labels and messages ("Select all", "Option X", "All options selected").
*
*  Logic:
*    The parent checkbox's onClick updates all child checkboxes to the opposite of the current parent state.
*    Each child checkbox's onCheckedChange updates its corresponding state in the childCheckedStates list.
*    The code displays "All options selected" when all child checkboxes are checked.
*
* Note: If you want to have a checkbox with an indeterminate state, you must use TriStateCheckbox. 
*       This is because it has a state parameter of type ToggleableState, whereas Checkbox does not.
*
**/
