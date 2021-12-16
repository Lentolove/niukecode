package zuoshen.sort;

/**
 * 创建大根堆
 */
public class MaxHeap {

    /**
     * 存储堆元素数组
     */
    private int[] heap;

    /**
     * 堆的大小限制，当然超过了会进行数组的两倍扩容
     */
    private final int capacity;

    /**
     * 表示目前这个堆收集了多少个数，也表示添加的下一个数应该放在哪个位置
     */
    private int heapSize;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        heap = new int[capacity];
        heapSize = 0;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean isFull() {
        return heapSize == capacity;
    }

    public void push(int value){
        if (heapSize == capacity){
            throw new RuntimeException("heap is full");
        }
        heap[heapSize] = value;
        //调整堆结构,heapSize作为操作数组的指针
        heapInsert(heap,heapSize++);
    }

    /**
     * 从堆中取出堆顶元素,即返回最大值，并且在大根堆中，
     * 把最大值删掉剩下的数，依然保持大根堆组织
     */
    public int pop(){
        if (heapSize == 0) {
            throw new RuntimeException("heap is empty");
        }
        int ans = heap[0];
        //这里的移除只是改变 heapSize 指针。至于数组的元素还存在对我们堆结构没有任何影响。因为我们在push操作中会覆盖当前值
        //我们将堆中最后一个元素移动到堆顶执行heapify操作，下沉堆顶元素
        swap(heap,0,--heapSize);
        //执行下沉操作
        heapify(heap,0,heapSize);
        return ans;
    }

    public int peek(){
        if (heapSize == 0) {
            throw new RuntimeException("heap is empty");
        }
        return heap[0];
    }

    /**
     * 向堆中插入元素
     */
    private void heapInsert(int[] arr,int index){
        //arr[index] 不比 arr[index父]大了，停止heapInsert过程
        //注意当比较到 0 位置即堆顶时候，此时循环也不会进入了
        while (arr[index] > arr[(index - 1) / 2]){
            swap(arr,index,(index - 1) / 2);
            //将指针移动到父节点，继续上升的过程
            index = (index - 1) / 2;
        }
    }

    /**
     * 从index位置，往下看，不断的下沉，停的条件：
     * 我的孩子都不再比我大；已经没孩子了
     */
    private void heapify(int[] arr,int index,int heapSize){
        //找到左孩子索引
        int leftIndex = index * 2 + 1;
        //这里如果左孩子越界，那右孩子更越界，右孩子等于左孩子+1
        while (leftIndex < heapSize){
            //下沉操作，父节点与左右孩子相比, 左右两个孩子中，谁大，谁把自己的下标给largest,因为大的节点得上升
            // 选择右  ->  (1) 有右孩子   && (2)右孩子的值比左孩子大才行
            int largest = leftIndex + 1 < heapSize && arr[leftIndex + 1] > arr[leftIndex] ?  leftIndex + 1 : leftIndex;
            //左右孩子中最大值，和当前值父节点比较，谁大谁把下标给largest(当前，左，右的最大值下标)
            largest = arr[largest] > arr[index] ? largest : index;
            //index 位置上的数比左右孩子的数都大，已经无需下沉
            if(index == largest) break;
            //交换父节点和子节点中的较大孩子。周而复始进行
            swap(arr,index,largest);
            //更新父节点的index，即父节点往下沉
            index = largest;
            //继续看它的左右孩子是否需要调整
            leftIndex = index * 2 + 1;
        }
    }

    private void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
