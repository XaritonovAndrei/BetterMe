const api = {
  listExercises:  () => fetch('/api/exercises').then(r => r.json()),
  createExercise: name =>
    fetch('/api/exercises', {
      method: 'POST',
      headers: {'Content-Type':'application/json'},
      body: JSON.stringify({ name })
    }).then(r => r.json()),
  deleteExercise: id =>
    fetch(`/api/exercises/${id}`, { method: 'DELETE' }),

  listEntries: date =>
    fetch(`/workout?date=${date}`).then(r => r.json()),
  recordEntry: ({exId,reps,sets,weight}) =>
    fetch('/workout/recordWorkout', {
      method:'POST',
      headers:{'Content-Type':'application/json'},
      body: JSON.stringify({ exerciseId: exId, reps, sets, weight })
    }).then(r=>r.json())
}

export default api
