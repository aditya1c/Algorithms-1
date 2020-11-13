import edu.princeton.cs.algs4.StdIn
import edu.princeton.cs.algs4.StdOut
import edu.princeton.cs.algs4.StdRandom

object Permutation {
    @JvmStatic
    fun main(args: Array<String>) {
        val k = args[0].toInt()
        val queue = RandomizedQueue<String>()

        // Implement reservoir sampling algorithm to tackle the bonus task
        var count = 0
        while (!StdIn.isEmpty()) {
            val s = StdIn.readString()
            count++
            if (queue.size() < k) {
                queue.enqueue(s)
            } else {
                if (StdRandom.uniform() < k.toDouble() / count) {
                    queue.dequeue()
                    queue.enqueue(s)
                }
            }
        }
        for (s in queue) StdOut.println(s)
    }
}