interface ScenarioOutcomeValidator<T, V> {
    fun validate(scenario: T, scenarioOutcome: V): Boolean
}