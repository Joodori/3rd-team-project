import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useUserInfo = defineStore('user', () => {

    const user = ref(true)

    const no_reserve = ref(false)

    const config = ref('')

    const loginStatus = ref(false)

    const user_info = ref({
        user_no: '',
        user_id : '',
        user_name : '',
        user_birth_date: '',
        user_address : '',
        user_mobile : ''
    })

    const ticket = ref([])

    const ride = ref([])


    return{
        user, no_reserve, config, loginStatus, user_info, ticket, ride
    }

})