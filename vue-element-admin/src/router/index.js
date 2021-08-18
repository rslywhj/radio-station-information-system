import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/* Router Modules */
import componentsRouter from './modules/components'
import chartsRouter from './modules/charts'
import tableRouter from './modules/table'
import nestedRouter from './modules/nested'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    noCache: true                if set true, the page will no be cached(default is false)
    affix: true                  if set true, the tag will affix in the tags-view
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/auth-redirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error-page/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error-page/401'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: 'Dashboard',
        meta: { title: '首页', icon: 'dashboard', affix: true }
      }
    ]
  },
  {
    path: '/check-in',
    component: Layout,
    redirect: '/check-in/index',
    children: [
      {
        path: 'index',
        component: () => import('@/views/check-in/index'),
        name: 'Check-in',
        meta: { title: '打卡', icon: 'el-icon-s-claim', noCache: true }
      }
    ]
  }
]

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export const asyncRoutes = [
  {
    path: '/calendar',
    component: Layout,
    meta: {
      title: '日程',
      icon: 'el-icon-date',
      roles: ['dudao', 'lanmuzongbian', 'edit', 'broadcast']
    },
    children: [
      {
        path: 'dudao',
        component: () => import('@/views/calendar/dudao'),
        name: 'dudaoCalendar',
        meta: {
          title: '日程',
          icon: 'el-icon-date',
          noCache: true,
          roles: ['dudao']
        }
      },
      {
        path: 'lmzb',
        component: () => import('@/views/calendar/lanmuzongbian'),
        name: 'lmzbCalendar',
        meta: {
          title: '日程',
          icon: 'el-icon-date',
          noCache: true,
          roles: ['lanmuzongbian']
        }
      },
      {
        path: 'index',
        component: () => import('@/views/calendar/date'),
        name: 'Calendar',
        meta: {
          title: '日程',
          icon: 'el-icon-date',
          noCache: true,
          roles: ['edit', 'broadcast']
        }
      }
    ]
  },
  {
    path: '/member-manager',
    component: Layout,
    meta: {
      roles: ['taizhang']
    },
    children: [
      {
        path: 'index',
        component: () => import('@/views/member-manager/page'),
        name: 'page',
        meta: {
          title: '人员管理',
          icon: 'peoples',
          roles: ['taizhang']
        }
      }
    ]
  },
  {
    path: '/feedback',
    component: Layout,
    meta: {
      roles: ['edit']
    },
    children: [
      {
        path: 'index',
        component: () => import('@/views/feedback/index'),
        name: 'page',
        meta: {
          title: '设备反馈',
          icon: 'clipboard',
          roles: ['edit']
        }
      }
    ]
  },

  {
    path: '/feedbackS',
    component: Layout,
    meta: {
      title: '查看反馈',
      icon: 'el-icon-question',
      roles: ['jishu']
    },
    children: [
      {
        path: 'index',
        component: () => import('@/views/feedback/select'),
        name: 'page',
        meta: {
          title: '查看需要解决的问题 ',
          icon: 'clipboard',
          roles: ['jishu']
        }
      },
      {
        path: 'selectAll',
        component: () => import('@/views/feedback/selectAll'),
        name: 'page',
        meta: {
          title: '查看全部问题 ',
          icon: 'clipboard',
          roles: ['jishu']
        }
      }
    ]
  },
  {
    path: '/selectGroup',
    component: Layout,
    meta: {
      roles: ['broadcastz']
    },
    children: [
      {
        path: 'index',
        component: () => import('@/views/check-in/group'),
        name: 'group',
        meta: { title: '查看今日打卡情况', icon: 'tree', noCache: true, roles: ['broadcastz'] }
      }
    ]
  },
  {
    path: '/selectAllGroup',
    component: Layout,
    meta: {
      roles: ['taizhang']
    },
    children: [
      {
        path: 'index',
        component: () => import('@/views/check-in/allgroup'),
        name: 'allgroup',
        meta: { title: '查看今日全台打卡情况', icon: 'tree', noCache: true, roles: ['taizhang'] }
      }
    ]
  },
  {
    path: '/see-aticles',
    component: Layout,
    meta: {
      title: '管理稿件',
      icon: 'documentation',
      roles: ['broadcastz','edit','broadcast']
    },
    children: [
      {
        path: '/dai',
        component: () => import('@/views/see-aticles/dai'),
        name: 'dai',
        meta: {
          title: '待处理稿件',
          icon: 'documentation',
          roles: ['broadcastz']
        }
      },
      {
        path: '/all',
        component: () => import('@/views/see-aticles/all'),
        name: 'all',
        meta: {
          title: '全部稿件',
          icon: 'documentation',
          roles: ['broadcastz']
        }
      },
      {
        path: '/allJ',
        component: () => import('@/views/see-aticles/allJ'),
        name: 'allJ',
        meta: {
          title: '全部稿件',
          icon: 'documentation',
          roles: ['edit']
        }
      },
      {
        path: '/art/:title',
        component: () => import('@/views/see-aticles/art'),
        name: 'arttt',
        hidden: true,
        meta: {
          title: '稿件',
          icon: 'documentation',
          roles: ['broadcastz','edit','broadcast']
        }
      }
    ]
  },
  {
    path: '/see-aticle',
    component: Layout,
    meta: {
      title: '管理稿件',
      icon: 'documentation',
      roles: ['lanmuzongbian']
    },
    children: [
      {
        path: 'daifu',
        component: () => import('@/views/see-aticles/daifu'),
        name: 'daifu',
        meta: {
          title: '待处理稿件',
          icon: 'documentation',
          roles: ['lanmuzongbian']
        }
      },
      {
        path: 'allT',
        component: () => import('@/views/see-aticles/allT'),
        name: 'allT',
        meta: {
          title: '全部稿件',
          icon: 'documentation',
          roles: ['lanmuzongbian']
        }
      },
      {
        path: '/art/:title',
        component: () => import('@/views/see-aticles/art'),
        name: 'art',
        hidden: true,
        meta: {
          title: '稿件',
          icon: 'documentation',
          roles: ['lanmuzongbian']
        }
      }
    ]
  },
  {
    path: '/example',
    component: Layout,
    redirect: '/example/list',
    name: 'Example',
    alwaysShow: true,
    meta: {
      title: '进台',
      icon: 'el-icon-microphone',
      roles: ['broadcast']
    },
    children: [
      {
        path: 'broadcast',
        component: () => import('@/views/documentation/broadcast'),
        name: 'Broadcast',
        meta: {
          title: '练稿',
          icon: 'el-icon-discount',
          roles: ['broadcast']
        }
      },
      {
        path: 'create',
        component: () => import('@/views/example/audition'),
        name: 'ReadArticle',
        meta: { title: '开播', icon: 'el-icon-phone-outline', roles: ['broadcast'] }
      }
    ]
  },
  {
    path: '/example1',
    component: Layout,
    redirect: '/example/list',
    name: 'Example',
    hidden: true,
    alwaysShow: true,
    meta: {
      title: '进台',
      icon: 'el-icon-microphone',
      roles: ['edit']
    },
    children: [
      {
        path: '/edit/:date',
        component: () => import('@/views/documentation/edit'),
        name: 'CreateArticle',
        hidden: true,
        meta: { title: '编稿', icon: 'edit', roles: ['edit'] }
      }
    ]
  },
  {
    path: '/permission',
    component: Layout,
    redirect: '/permission/page',
    alwaysShow: true, // will always show the root menu
    name: 'Permission',
    meta: {
      title: '操作',
      icon: 'lock'
      //roles: ['admin:teach', 'admin:exam'] // you can set roles in root nav
    },
    children: [
      {
        path: 'page',
        component: () => import('@/views/permission/page'),
        name: 'PagePermission',
        meta: {
          title: '页面权限'
          //roles: ['admin:teach'] // or you can only set roles in sub nav
        }
      },
      {
        path: 'directive',
        component: () => import('@/views/permission/directive'),
        name: 'DirectivePermission',
        meta: {
          title: 'Directive Permission'
          // if do not set roles, means: this page does not require permission
        }
      },
      {
        path: 'role',
        component: () => import('@/views/permission/role'),
        name: 'RolePermission',
        meta: {
          title: '角色'
          //roles: ['admin:exam']
        }
      }
    ]
  },
  {
    path: '/tab',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/tab/index'),
        name: 'Tab',
        meta: { title: 'Tab', icon: 'tab' }
      }
    ]
  },

  {
    path: '/error',
    component: Layout,
    redirect: 'noRedirect',
    name: 'ErrorPages',
    meta: {
      title: 'Error Pages',
      icon: '404'
    },
    children: [
      {
        path: '401',
        component: () => import('@/views/error-page/401'),
        name: 'Page401',
        meta: { title: '401', noCache: true }
      },
      {
        path: '404',
        component: () => import('@/views/error-page/404'),
        name: 'Page404',
        meta: { title: '404', noCache: true }
      }
    ]
  },

  {
    path: '/error-log',
    component: Layout,
    children: [
      {
        path: 'log',
        component: () => import('@/views/error-log/index'),
        name: 'ErrorLog',
        meta: { title: 'Error Log', icon: 'bug' }
      }
    ]
  },

  {
    path: '/excel',
    component: Layout,
    redirect: '/excel/export-excel',
    name: 'Excel',
    meta: {
      title: 'Excel',
      icon: 'excel'
    },
    children: [
      {
        path: 'export-excel',
        component: () => import('@/views/excel/export-excel'),
        name: 'ExportExcel',
        meta: { title: 'Export Excel' }
      },
      {
        path: 'export-selected-excel',
        component: () => import('@/views/excel/select-excel'),
        name: 'SelectExcel',
        meta: { title: 'Export Selected' }
      },
      {
        path: 'export-merge-header',
        component: () => import('@/views/excel/merge-header'),
        name: 'MergeHeader',
        meta: { title: 'Merge Header' }
      },
      {
        path: 'upload-excel',
        component: () => import('@/views/excel/upload-excel'),
        name: 'UploadExcel',
        meta: { title: 'Upload Excel' }
      }
    ]
  },

  {
    path: '/zip',
    component: Layout,
    redirect: '/zip/download',
    alwaysShow: true,
    name: 'Zip',
    meta: { title: 'Zip', icon: 'zip' },
    children: [
      {
        path: 'download',
        component: () => import('@/views/zip/index'),
        name: 'ExportZip',
        meta: { title: 'Export Zip' }
      }
    ]
  },

  {
    path: '/pdf',
    component: Layout,
    redirect: '/pdf/index',
    children: [
      {
        path: 'index',
        component: () => import('@/views/pdf/index'),
        name: 'PDF',
        meta: { title: 'PDF', icon: 'pdf' }
      }
    ]
  },
  {
    path: '/pdf/download',
    component: () => import('@/views/pdf/download'),
    hidden: true
  },

  {
    path: '/theme',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/theme/index'),
        name: 'Theme',
        meta: { title: 'Theme', icon: 'theme' }
      }
    ]
  },

  {
    path: '/clipboard',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/clipboard/index'),
        name: 'ClipboardDemo',
        meta: { title: 'Clipboard', icon: 'clipboard' }
      }
    ]
  },

  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'https://github.com/PanJiaChen/vue-element-admin',
        meta: { title: 'External Link', icon: 'link' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
